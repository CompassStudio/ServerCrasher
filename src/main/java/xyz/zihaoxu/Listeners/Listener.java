package xyz.zihaoxu.Listeners; // xyz.zihaoxu.Listeners

import com.github.steveice10.mc.protocol.data.game.ResourcePackStatus;
import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundLoginPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.entity.player.ClientboundPlayerPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundResourcePackPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.player.ServerboundMovePlayerPosPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.player.ServerboundSwingPacket;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.*;
import com.github.steveice10.packetlib.packet.Packet;
import xyz.zihaoxu.Main;
import xyz.zihaoxu.Configure;
import xyz.zihaoxu.script.obj.ScriptBot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

// FROM Zihaoxu2008
// 现在是11:32 a.m.
// 我困了,让我睡会,这个类基本没混淆
public class Listener implements SessionListener { // Class: Listener
    private final ScriptBot bot;

    private double posX;
    private double posY;
    private double posZ;
    private boolean isLoggedIn;
    public Listener(ScriptBot bot) {
        this.bot = bot;
    }

    public void sendChatMessage(Session session,String message) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (Configure.high_version){
            Class<?> clazz=Class.forName("com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket");
            Constructor<?> constructor=clazz.getDeclaredConstructor(String.class,long.class,long.class,byte[].class,int.class,BitSet.class);
            session.send(
                    (Packet) constructor.newInstance(
                            message,
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
            session.send((Packet) constructor.newInstance(message));
        }
    }

    public void sendCommand(Session session,String command) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(Configure.high_version) {
            Class<?> clazz = Class.forName("com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatCommandPacket");
            Constructor<?> c = clazz.getDeclaredConstructor(String.class, long.class, long.class, List.class, int.class, BitSet.class);
            session.send(
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
            this.sendChatMessage(session,"/"+command);
        }
    }

    @Override
    public void packetReceived(Session session, Packet packet) {
        if (packet instanceof ClientboundLoginPacket){
            Main.scriptManager.call("bot_login", bot);
//            sendChatMessage(session,"helloa1");
//            sendChatMessage(session,"helloa1");
//            sendCommand(session,"server sc1");
            this.isLoggedIn=true;
        }else if (packet instanceof ClientboundPlayerPositionPacket){
            posX=((ClientboundPlayerPositionPacket) packet).getX();
            posY=((ClientboundPlayerPositionPacket) packet).getY();
            posZ=((ClientboundPlayerPositionPacket) packet).getZ();
        }

        if (isLoggedIn){
            if (!Configure.spammer_text.isEmpty()){
                try {
                    sendChatMessage(session, Configure.spammer_text);
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                         InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            session.send(new ServerboundSwingPacket(Hand.MAIN_HAND));
            session.send(new ServerboundSwingPacket(Hand.OFF_HAND));
            session.send(new ServerboundResourcePackPacket(ResourcePackStatus.FAILED_DOWNLOAD));
            posY-=0.5d;
            session.send(new ServerboundMovePlayerPosPacket(true,posX,posY,posZ));

        }
        Main.scriptManager.call("bot_update", bot);
    }

    @Override
    public void packetSending(PacketSendingEvent event) {

    }

    @Override
    public void packetSent(Session session, Packet packet) {

    }

    @Override
    public void packetError(PacketErrorEvent event) {

    }

    @Override
    public void connected(ConnectedEvent event) {

    }

    @Override
    public void disconnecting(DisconnectingEvent event) {

    }

    @Override
    public void disconnected(DisconnectedEvent event) {
    }

}
