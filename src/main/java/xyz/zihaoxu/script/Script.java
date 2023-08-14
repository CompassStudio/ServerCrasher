package xyz.zihaoxu.script;

import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.NashornScriptEngine;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import xyz.zihaoxu.script.obj.ScriptLogger;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Script {
    private final NashornScriptEngine engine = (NashornScriptEngine) new NashornScriptEngineFactory().getScriptEngine();
    private final List<ScriptEventListener> listeners = new ArrayList<>();

    private String name;
    private String author;
    private String version;

    public Script(String content) throws ScriptException {
        // 静态项
        engine.put("logger", new ScriptLogger(this));

        // 公开函数
        engine.put("register", new Register());

        // 载入主脚本
        engine.eval(content);
        call("startup");
    }

    class Register implements Function<JSObject, Script>  {
        @Override
        public Script apply(JSObject o) {
            name = o.getMember("name").toString();
            author = o.getMember("author").toString();
            version = o.getMember("version").toString();
            return Script.this;
        }
    }

    public void listen(String name, JSObject callback) {
        listeners.add(new ScriptEventListener(name, this, callback));
    }

    public void call(String name, Object... args) {
        for (ScriptEventListener listener: listeners) {
            if (listener.event().equals(name)) {
                listener.callback().call(null, args);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }
}
