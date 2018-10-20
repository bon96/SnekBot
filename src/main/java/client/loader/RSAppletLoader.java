package client.loader;



import client.loader.gui.BotFrame;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by: Tommi
 * Date: 27.10.2017
 * Time: 15.20
 */
public class RSAppletLoader {
    private RSAppletStub appletStub;
    public BotFrame frame;

    protected Applet applet;
    protected URLClassLoader classLoader;

    public static int WORLD = 8;

    public RSAppletLoader() { init(); }

    public void init() {
        ConfigReader configReader = new ConfigReader("http://oldschool" + Integer.toString(WORLD) + ".runescape.com/k=3/jav_config.ws?userFlow=7109181994244395332");
        Map<String, String> map = configReader.read();
        String jarLocation = map.get("codebase") + map.get("initial_jar");
        try {
            classLoader = new URLClassLoader(new URL[]{new URL(jarLocation)});
            Class<?> clientClass = classLoader.loadClass(map.get("initial_class").replace(".class", ""));
            applet = (Applet) clientClass.newInstance();
            appletStub = new RSAppletStub(map);
            appletStub.getAppletContext().setApplet(applet);
            applet.setStub(appletStub);
            applet.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        applet.setSize(new Dimension(800, 600));
        appletStub.setActive(true);
        frame = new BotFrame(applet);
        frame.init();

    }
}
