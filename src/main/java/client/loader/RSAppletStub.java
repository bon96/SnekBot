package client.loader;

import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by: Tommi
 * Date: 27.10.2017
 * Time: 13.57
 */

public class RSAppletStub implements AppletStub {

    private final Map<String, String> parameters;
    private final RSAppletContext appletContext;
    private boolean active = false;

    public RSAppletStub(Map<String, String> parameters) {
        this.parameters = parameters;
        appletContext = new RSAppletContext();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public URL getDocumentBase() {
        try {
            return new URL(parameters.get("codebase"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Document Base URL");
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(parameters.get("codebase"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Code Base URL");
        }
    }

    @Override
    public String getParameter(String name) {
        if (parameters.containsKey(name))
            return parameters.get(name);
        return null;
    }

    @Override
    public RSAppletContext getAppletContext() {
        return appletContext;
    }

    @Override
    public void appletResize(int width, int height) {
        //Applet applet = getAppletContext().getApplet("main");
        //if (applet != null) applet.resize(width, height);
    }
}
