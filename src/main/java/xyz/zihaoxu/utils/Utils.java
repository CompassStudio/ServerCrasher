package xyz.zihaoxu.utils;

import xyz.zihaoxu.Main;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {
    public static List<String> proxies =null; // proxies
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
