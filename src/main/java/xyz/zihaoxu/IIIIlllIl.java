package xyz.zihaoxu;

import com.github.steveice10.packetlib.ProxyInfo;
import xyz.zihaoxu.iillIlllI.IllIlIIlllIllI;
import xyz.zihaoxu.IlllIlII.IIIIllllIllIl;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Random;

public class IIIIlllIl implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                String emmm= llllllllllII.aaaaaa(llllllllllII.anString);
                System.out.println(emmm+" Creating");
                ProxyInfo llIlllIl=null;
                if(llllllllllII.aaawww!=null){
                    String lllIIIll=llllllllllII.aaawww.get(new Random().nextInt(llllllllllII.aaawww.size()));
                    llIlllIl=new ProxyInfo(
                            llllllllllII.IIlllIL,
                            new InetSocketAddress(
                                    lllIIIll.split(":")[0],
                                    Integer.parseInt(lllIIIll.split(":")[1])
                            )
                    );
                }
                IIIIllllIllIl HelloWorld=new IIIIllllIllIl(
                        llllllllllII.lllIlIll,
                        llllllllllII.IllIlIl,
                        emmm,
                        llIlllIl
                );
                if (!llllllllllII.aaawwawwa){
                    HelloWorld.println(new IllIlIIlllIllI());
                }
                HelloWorld.exit();
                HelloWorld=null;
                // System.gc();
                // Thread.sleep(1000);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}