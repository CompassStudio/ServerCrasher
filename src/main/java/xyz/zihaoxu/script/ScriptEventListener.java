package xyz.zihaoxu.script;

import org.openjdk.nashorn.api.scripting.JSObject;

public record ScriptEventListener(String event, Script script, JSObject callback) {

}
