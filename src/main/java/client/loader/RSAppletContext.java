package client.loader;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by: Tommi
 * Date: 27.10.2017
 * Time: 13.53
 */

public class RSAppletContext implements AppletContext {

    private final Map<String, InputStream> streams = new HashMap<>();
    private Applet applet;

    public AudioClip getAudioClip(URL url) {
        return Applet.newAudioClip(url);
    }

    public Image getImage(URL url) {
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Applet getApplet(String name) {
        return applet;
    }

    public Enumeration<Applet> getApplets() {
        Vector<Applet> applets = new Vector<>();
        applets.add(applet);
        return applets.elements();
    }

    public void showDocument(URL url) {
        /*
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException("Unable to open document " + url.getPath());
            }
        }
        */
    }

    public void showDocument(URL url, String target) {
        /*
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException("Unable to open document " + url.getPath());
            }
        }
        */
    }

    public void showStatus(String status) {


    }

    public void setStream(String key, InputStream stream) throws IOException {
        if (streams.containsKey(key)) {
            streams.remove(key);
        }
        streams.put(key, stream);
    }

    public InputStream getStream(String key) {
        return streams.get(key);
    }

    public Iterator<String> getStreamKeys() {
        return streams.keySet().iterator();
    }

    public void setApplet(Applet applet) {
        this.applet = applet;
    }
}