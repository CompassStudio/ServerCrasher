package xyz.zihaoxu.IlllIlII; // xyz.zihaoxu.protocol

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatCommandPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket;
import com.github.steveice10.packetlib.BuiltinFlags;
import com.github.steveice10.packetlib.ProxyInfo;
import com.github.steveice10.packetlib.event.session.SessionListener;
import com.github.steveice10.packetlib.packet.PacketProtocol;
import com.github.steveice10.packetlib.tcp.TcpClientSession;
import org.jetbrains.annotations.Nullable;
import xyz.zihaoxu.Main;
import xyz.zihaoxu.script.ScriptManager;
import xyz.zihaoxu.script.obj.ScriptBot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.BitSet;
import java.util.Collections;

public class IIIIllllIllIl { // Class: Bot
    public TcpClientSession OhMyGod; // client
    private final ScriptBot script;

    // 祖安式传参
    public IIIIllllIllIl(String WhatTheFuck/*host*/, int HolyShit/*port*/, String HolyCrap/*nickname*/, @Nullable ProxyInfo Shit) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 传统艺能: 通过反射获取需要的类
        Class<?> FAQ = Class.forName("com.github.steveice10.packetlib.tcp.TcpClientSession"); // class
        if(Shit==null) { // 瞄一眼要不要代理
            Constructor<?> Minecraft = FAQ.getDeclaredConstructor(String.class, int.class, PacketProtocol.class); // constructor
            this.OhMyGod = (TcpClientSession) Minecraft.newInstance(WhatTheFuck, HolyShit, new MinecraftProtocol(HolyCrap));
        }else {
            Constructor<?> Minecraft = FAQ.getDeclaredConstructor(String.class, int.class, PacketProtocol.class,ProxyInfo.class); // constructor
            this.OhMyGod = (TcpClientSession) Minecraft.newInstance(WhatTheFuck, HolyShit, new MinecraftProtocol(HolyCrap),Shit);
        }

        // this.OhMyGod.setFlag(BuiltinFlags.PRINT_DEBUG,true);

        /*
        * if(proxy_info==null){
        *   this.client=new TcpClientSession(host,port,new MinecraftProtocol(nickname));
        * }else{
        *   this.client=new TcpClientSession(host,port,new MinecraftProtocol(nickname),proxy_info);
        * */
        script = new ScriptBot(this);
        script.name=HolyCrap;
        Main.scriptManager.call("bot_created", HolyCrap, script);
    }

    // 嗯对,原神传参
    // 这个是addListener(SessionListener listener);
    public void println(SessionListener Genshin) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException { // addListener(Listener)
        Class<?> clazz=this.OhMyGod.getClass(); // 日常反射
        Method method=clazz.getMethod("addListener", SessionListener.class);
        method.invoke(this.OhMyGod,Genshin);
    }

    public void exit() throws InterruptedException { // connect()
        Main.scriptManager.call("bot_pre_connect", script);
        this.OhMyGod.setConnectTimeout(3); // 防止在一个代理上吊死
        this.OhMyGod.connect(true);
        while (this.OhMyGod.isConnected()){
            Thread.sleep(1000);
        }
    }

    public void lllIlIll(String OP){ // sendChatMessage(String msg);
        this.OhMyGod.send(
                new ServerboundChatPacket(
                        OP,
                        Instant.now().toEpochMilli(),
                        0L,
                        null,
                        0,
                        new BitSet()
                )
        );
    }

    public void sendCommand(String command){
        this.OhMyGod.send(
                new ServerboundChatCommandPacket(
                        command,
                        Instant.now().toEpochMilli(),
                        0L,
                        Collections.emptyList(),
                        0,
                        new BitSet()
                )
        );
    }

    public ScriptBot getScript() {
        return script;
    }
}
