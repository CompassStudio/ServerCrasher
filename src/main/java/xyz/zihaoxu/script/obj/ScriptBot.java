package xyz.zihaoxu.script.obj;

import xyz.zihaoxu.protocol.Bot;

import java.lang.reflect.InvocationTargetException;

public class ScriptBot {
    private final Bot instance;
    public String name;

    public ScriptBot(Bot instance) {
        this.instance = instance;
    }

    public void sendMessage(String message) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        instance.sendChatMessage(message);
    }
    public void sendCommand(String command) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { instance.sendCommand(command);}
    public String getName(){return this.name;}
}
