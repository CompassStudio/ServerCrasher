package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class llllllllllII { // Class: Configure 这个是配置
    public static String lllIlIll; // host: String
    public static int IllIlIl; // port: int
    public static String anString; // name_prefix: String
    public static int an114514; // thread_count: int
    public static String ananan =""; // spammer_text: String
    public static String aawawa = ""; // proxy_file: String
    public static List<String> aaawww=null; // proxies
    public static ProxyInfo.Type IIlllIL = ProxyInfo.Type.SOCKS4; // proxy_type
    public static boolean aaawwawwa=false; // 玛卡巴卡 这个应该是控制要不要加Listener的

    public static String aaaaaa(String awa){ // generateRandomName(String prefix)
        StringBuilder qwq=new StringBuilder(); // StringBuilder sb
        qwq.append(awa);
        Random qaq=new Random();
        int len=qaq.nextInt(4,10);
        for (int i=0;i<len;i++){
            if (qaq.nextBoolean()) {
                qwq.append((char) (qaq.nextInt(26) + 'a'));
            }else {
                qwq.append((char) (qaq.nextInt(26) + 'A'));
            }
        }

        // 允许脚本更改生成的名称
        AtomicReference<String> atom = new AtomicReference<>();
        atom.set(qwq.toString());
        Main.scriptManager.call("random_name_gen", atom);

        return atom.get();
    }
}
