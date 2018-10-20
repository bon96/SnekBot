package client;

import client.hooks.DBHookLoader;
import client.hooks.HookLoader;
import client.hooks.RSField;
import client.loader.RSAppletLoader;

import java.applet.Applet;
import java.awt.*;
import java.net.URLClassLoader;
import java.util.Map;

/**
 * BonBom
 * Date: 3.11.2017
 * Time: 23.06
 */

public class BotInstance extends RSAppletLoader {
    DBHookLoader hookLoader;
    public BotInstance() {
        super();
        hookLoader = new DBHookLoader(classLoader);
    }


    public DBHookLoader getHookLoader() {
        return hookLoader;
    }


    public Applet getApplet() {
        return super.applet;
    }

    public Canvas getCanvas() {
        return (Canvas) applet.getComponent(0);
    }


    public URLClassLoader getClassLoader() {
        return super.classLoader;
    }


    public Object getRawFieldValue(String field) {
        return getHookLoader().getHookMap().get(field).getValue();
    }

    public static void main(String[] args) {
        HookLoader hookLoader = new BotInstance().getHookLoader();
        for (Map.Entry<String, RSField> entry : hookLoader.getHookMap().entrySet()) {
            System.out.println(entry.getKey());
        }
        for (Map.Entry<String, String> entry : hookLoader.getClassNameMap().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
