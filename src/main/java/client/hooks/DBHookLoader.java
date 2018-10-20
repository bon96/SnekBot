package client.hooks;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * hooks stolen from DreamBot. Sorry Nezz :(
 * Date: 3.11.2017
 * Time: 17.22
 */

public class DBHookLoader extends HookLoader {

    public DBHookLoader(URLClassLoader classLoader) {
        super(classLoader);
        processDBHooks();
    }


    public List<String> getDreambotHooks() {
        String full = getHTML("http://cdn.dreambot.org/hooks.txt");
        String[] lines = full.split("\n");
        List<String> hooks = new ArrayList<String>();
        for (String line : lines) {
            if (line.length() > 5 && !line.contains("@") && !line.contains("#") && !line.contains("broken")) {
                hooks.add(line);
            }
            if (line.contains("@")) {
                String name = line.split("@")[1].split("\\s+")[0];
                String originalName = line.split(":")[1].split("\\s+")[1];
                super.classNameMap.put(name, originalName);
                super.classMap.put(name,
                        new RSClass(this, name, originalName));
            }
        }
        return hooks;
    }

    private void processDBHooks() {
        List<String> hooks = getDreambotHooks();
        for (String hook : hooks) {
            super.addHook(getRsField(hook));
        }
    }


    private String getHTML(String url) {
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return content;
    }

}
