package api;

import api.input.Keyboard;
import api.input.Mouse;
import api.methods.*;
import api.utilities.Condition;
import api.wrappers.RSClient;
import client.BotInstance;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 12.37
 */

public class MethodContext {
    BotInstance instance;

    public MethodContext() {
        this.instance = new BotInstance();
        startGraphics();
    }

    public MethodContext(BotInstance instance) {
        this.instance = instance;
    }

    public BotInstance getInstance() {
        return instance;
    }

    Inventory inventory;
    public Inventory getInventory() {
        if (inventory == null) {
            inventory = new Inventory(this);
        }
        return inventory;
    }

    Widgets widgets;
    public Widgets getWidgets() {
        if (widgets == null) {
            widgets = new Widgets(this);
        }
        return widgets;
    }

    Players players;
    public Players getPlayers() {
        if (players == null) {
            players = new Players(this);
        }
        return players;
    }

    Npcs npcs;
    public Npcs getNpcs() {
        if (npcs == null) {
            npcs = new Npcs(this);
        }
        return npcs;
    }

    public static Keyboard keyboard;
    public Keyboard getKeyboard() {
        if (keyboard == null) {
            keyboard = new Keyboard(instance);
        }
        return keyboard;
    }

    public static Mouse mouse;
    public Mouse getMouse() {
        if (mouse == null) {
            mouse = new Mouse(instance);
        }
        return mouse;
    }

    RSClient client;
    public RSClient getClient() {
        if (client == null) {
            client = new RSClient(instance, null);
        }
        return client;
    }

    Camera camera;
    public Camera getCamera() {
        if (camera == null) {
            camera = new Camera(this);
        }
        return camera;
    }

    public Player getLocalPlayer() {
        return new Player(this, getClient().getLocalPlayer());
    }


    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean sleepUntil(Condition condition, int max) {
        int count = (int) max/100;
        int loops = 0;
        while(!condition.verify()) {
            if (loops > count) {
                break;
            }
            sleep(100);
            loops++;
        }
        return condition.verify();
    }


    private void startGraphics() {
        try {
            new Thread(() -> {
                while (true) {
                    try {
                        paint();
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage canvas = new BufferedImage(764, 503,
            BufferedImage.TYPE_INT_ARGB);

    private void paint() {
        Graphics g = canvas.getGraphics();

        int cursorSize = 10;
        int x = Mouse.point.x;
        int y = Mouse.point.y;

        g.drawLine(x, y, x+cursorSize, y);
        g.drawLine(x, y, x-cursorSize, y);
        g.drawLine(x, y, x, y+cursorSize);
        g.drawLine(x, y, x, y-cursorSize);
        onPaint(g);
    }

    public void onPaint(Graphics g){}
}
