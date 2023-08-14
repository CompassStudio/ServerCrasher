package xyz.zihaoxu.script.obj;

import xyz.zihaoxu.script.Script;

public class ScriptLogger extends ScriptObject {
    public ScriptLogger(Script instance) {
        super(instance);
    }

    public void info(String content) {
        System.out.println("[" + instance.getName() + "/INFO] " + content);
    }

    public void error(String content) {
        System.err.println("[" + instance.getName() + "/ERROR] " + content);
    }
}
