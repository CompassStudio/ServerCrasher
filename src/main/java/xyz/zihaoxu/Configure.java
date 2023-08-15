package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Configure { // Class: Configure 这个是配置
    public static String host; // host: String
    public static int port; // port: int
    public static String name_prefix; // name_prefix: String
    public static int thread_count; // thread_count: int
    public static String spammer_text =""; // spammer_text: String
    public static String proxy_file = ""; // proxy_file: String
    public static List<String> proxies =null; // proxies
    public static ProxyInfo.Type proxy_type = ProxyInfo.Type.SOCKS4; // proxy_type
    public static boolean should_add_listener = false; // 玛卡巴卡 这个应该是控制要不要加Listener的
    public static boolean high_version = true;

    public static String generate_random_name(String awa){ // generateRandomName(String prefix)
        StringBuilder sb=new StringBuilder(); // StringBuilder sb
        sb.append(awa);
        Random rand=new Random();
        int len=rand.nextInt(4,10);
        for (int i=0;i<len;i++){
            if (rand.nextBoolean()) {
                sb.append((char) (rand.nextInt(26) + 'a'));
            }else {
                sb.append((char) (rand.nextInt(26) + 'A'));
            }
        }

        // 允许脚本更改生成的名称
        AtomicReference<String> atom = new AtomicReference<>();
        atom.set(sb.toString());
        Main.scriptManager.call("random_name_gen", atom);

        return atom.get();
    }
}
