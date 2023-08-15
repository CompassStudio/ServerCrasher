package xyz.zihaoxu;

import xyz.zihaoxu.script.ScriptManager;

import javax.script.ScriptException;
import java.io.IOException;

// 该死的主类
// 他妈的之前告诉我找不到主类我操了
public class Main {
    public static ScriptManager scriptManager = new ScriptManager();

    public static void main(String[] args) {
        try {
            scriptManager.init();
            new ServerCrasher(args).run();
        } catch (IOException | ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}
