package xyz.zihaoxu.iillIlllI; // xyz.zihaoxu.Listeners

import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundDisconnectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundLoginPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.player.ServerboundSwingPacket;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.*;
import com.github.steveice10.packetlib.packet.Packet;
import xyz.zihaoxu.llllllllllII;

import java.time.Instant;
import java.util.BitSet;

public class IllIlIIlllIllI implements SessionListener { // Class: Listener
    @Override
    public void packetReceived(Session session, Packet packet) {
        if (packet instanceof ClientboundLoginPacket){
            session.addListener(new SessionAdapter(){
                @Override
                public void packetReceived(Session session, Packet packet) {
                    super.packetReceived(session, packet);
                    if (!llllllllllII.ananan.isEmpty()){
                        session.send(new ServerboundChatPacket(
                                llllllllllII.ananan,
                                Instant.now().toEpochMilli(),
                                0L,
                                null,
                                0,
                                new BitSet()
                        ));
                    }
                    session.send(new ServerboundSwingPacket(Hand.MAIN_HAND));
                    session.send(new ServerboundSwingPacket(Hand.OFF_HAND));
                }
            });
        }
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
        try {
            finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}