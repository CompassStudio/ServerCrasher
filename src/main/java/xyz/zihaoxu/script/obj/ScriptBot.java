package xyz.zihaoxu.script.obj;

import xyz.zihaoxu.IlllIlII.IIIIllllIllIl;

public class ScriptBot {
    private final IIIIllllIllIl instance;
    public String name;

    public ScriptBot(IIIIllllIllIl instance) {
        this.instance = instance;
    }

    public void sendMessage(String message) {
        instance.lllIlIll(message);
    }
    public void sendCommand(String command) { instance.sendCommand(command);}
    public String getName(){return this.name;}
}
