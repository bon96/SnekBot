package client.hooks;


import java.applet.Applet;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Tommi
 * Date: 27.10.2017
 * Time: 16.27
 */

public class HookLoader {
    URLClassLoader classLoader;
    Map<String, RSField> hookMap = new HashMap<>();
    Map<String, String> classNameMap = new HashMap<>();
    Map<String, RSClass> classMap = new HashMap<>();

    public HookLoader(URLClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    protected RSField getRsField(String hookValue) {
        String[] args = hookValue.split("\\.|\\s+");

        if (args.length < 4) {
            System.out.println(hookValue);
            return null;
        }
        if (args.length > 4) {
            if (args[4].contains("L")) {
                return new RSField(this, args[0], args[1], args[2], args[3], Long.parseLong(args[4].split("L")[0]));
            } else if (args[4].contains("D")) {
                return new RSField(this, args[0], args[1], args[2], args[3], Double.parseDouble(args[4].split("D")[0]));
            } else {
                return new RSField(this, args[0], args[1], args[2], args[3], Integer.parseInt(args[4]));
            }
        } else {
            return new RSField(this, args[0], args[1], args[2], args[3], 0);
        }
    }

    public Object getFieldValue(String string) {
        return getFieldValue(string, null);
    }

    public Object getFieldValue(String string, Object object) {
            RSField field = getHookMap().get(string);
            if (field == null) {
                System.out.println("Couldn't find field " + string);
                return null;
            } else {
                return field.getValue(object);
            }
    }

    protected void addHook(RSField rsField) {
            hookMap.put(rsField.realClassName + "." + rsField.realFieldName, rsField);
    }

    public Map<String, RSField> getHookMap() {
        return hookMap;
    }

    public Map<String, String> getClassNameMap() {
        return classNameMap;
    }

    public Map<String, RSClass> getClassMap() {
        return classMap;
    }

    public Class loadClass(String className) {
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
