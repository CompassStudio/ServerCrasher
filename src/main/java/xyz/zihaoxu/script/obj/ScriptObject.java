package xyz.zihaoxu.script.obj;

import xyz.zihaoxu.script.Script;

public abstract class ScriptObject {
    protected final Script instance;

    public ScriptObject(Script instance) {
        this.instance = instance;
    }
}
