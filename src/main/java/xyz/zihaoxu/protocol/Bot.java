package xyz.zihaoxu.protocol; // xyz.zihaoxu.protocol

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.packetlib.ProxyInfo;
import com.github.steveice10.packetlib.event.session.SessionListener;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.packetlib.packet.PacketProtocol;
import com.github.steveice10.packetlib.tcp.TcpClientSession;
import org.jetbrains.annotations.Nullable;
import xyz.zihaoxu.Main;
import xyz.zihaoxu.script.obj.ScriptBot;
import xyz.zihaoxu.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class Bot { // Class: Bot
    public TcpClientSession client; // client
    private final ScriptBot script;

    // 祖安式传参
    public Bot(String host/*host*/, int port/*port*/, String nickname/*nickname*/, @Nullable ProxyInfo proxy) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 传统艺能: 通过反射获取需要的类
        Class<?> FAQ = Class.forName("com.github.steveice10.packetlib.tcp.TcpClientSession"); // class
        if(proxy==null) { // 瞄一眼要不要代理
            Constructor<?> Minecraft = FAQ.getDeclaredConstructor(String.class, int.class, PacketProtocol.class); // constructor
            this.client = (TcpClientSession) Minecraft.newInstance(host, port, new MinecraftProtocol(nickname));
        }else {
            Constructor<?> Minecraft = FAQ.getDeclaredConstructor(String.class, int.class, PacketProtocol.class,ProxyInfo.class); // constructor
            this.client = (TcpClientSession) Minecraft.newInstance(host, port, new MinecraftProtocol(nickname),proxy);
        }

        // this.OhMyGod.setFlag(BuiltinFlags.PRINT_DEBUG,true);

        /*
        * if(proxy_info==null){
        *   this.client=new TcpClientSession(host,port,new MinecraftProtocol(nickname));
        * }else{
        *   this.client=new TcpClientSession(host,port,new MinecraftProtocol(nickname),proxy_info);
        * */
        script = new ScriptBot(this);
        script.name=nickname;
        Main.scriptManager.call("bot_created", nickname, script);
    }

    // 嗯对,原神传参
    // 这个是addListener(SessionListener listener);
    public void addListener(SessionListener listener) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException { // addListener(Listener)
//        Class<?> clazz=this.client.getClass(); // 日常反射
//        Method method=clazz.getMethod("addListener", SessionListener.class);
//        method.invoke(this.client,listener);
        this.client.addListener(listener);
    }

    public void run() throws InterruptedException { // connect()
        Main.scriptManager.call("bot_pre_connect", script);
        this.client.setConnectTimeout(5); // 防止在一个代理上吊死
        this.client.connect(true);
        Thread.sleep(3000);
        while (this.client.isConnected()){
            Thread.sleep(1000);
        }
    }

    // 这里为了向下兼容才用的反射
    public void sendChatMessage(String msg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // sendChatMessage(String msg);
//        this.OhMyGod.send(
//                new ServerboundChatPacket(
//                        msg,
//                        Instant.now().toEpochMilli(),
//                        0L,
//                        null,
//                        0,
//                        new BitSet()
//                )
//        );
        if (Utils.high_version){
            Class<?> clazz=Class.forName("com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket");
            Constructor<?> constructor=clazz.getDeclaredConstructor(String.class,long.class,long.class,byte[].class,int.class,BitSet.class);
            this.client.send(
                    (Packet) constructor.newInstance(
                            msg,
                            Instant.now().toEpochMilli(),
                            0L,
                            null,
                            0,
                            new BitSet()
                    )
            );
        }else {
            Class<?> clazz=Class.forName("com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket");
            Constructor<?> constructor=clazz.getDeclaredConstructor(String.class);
            this.client.send((Packet) constructor.newInstance(msg));
        }
    }

    public void sendCommand(String command) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(Utils.high_version) {
            Class<?> clazz = Class.forName("com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatCommandPacket");
            Constructor<?> c = clazz.getDeclaredConstructor(String.class, long.class, long.class, List.class, int.class, BitSet.class);
            this.client.send(
                    (Packet) c.newInstance(
                            command,
                            Instant.now().toEpochMilli(),
                            0L,
                            Collections.emptyList(),
                            0,
                            new BitSet()
                    )
            );
        }else {
            this.sendChatMessage("/"+command);
        }
//        this.OhMyGod.send(
//                new ServerboundChatCommandPacket(
//                        command,
//                        Instant.now().toEpochMilli(),
//                        0L,
//                        Collections.emptyList(),
//                        0,
//                        new BitSet()
//                )
//        );
    }

    public ScriptBot getScript() {
        return script;
    }
}
