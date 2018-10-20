package api.input;

import api.MethodContext;
import api.utilities.Condition;
import api.utilities.Random;
import client.BotInstance;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * BonBom
 * Date: 4.1.2018
 * Time: 12.34
 */

public class Keyboard implements KeyListener{
    public static BotInstance instance;

    public Keyboard(BotInstance instance) {
        this.instance  = instance;
        instance.getApplet().getComponent(0).addKeyListener(this);
    }


    public synchronized  void pressEnter() {
        pressKey(KeyEvent.VK_ENTER);
        MethodContext.sleep(Random.nextInt(60, 100));
    }

    public synchronized void writeText(String text, boolean pressEnter) {
        for (char character : text.toCharArray()) {
            typeKey(character);
        }
        if (pressEnter) {
            pressEnter();
        }
    }

    public synchronized void typeKey(char c) {
        AWTKeyStroke keystroke = AWTKeyStroke.getAWTKeyStroke(c);
        int keycode = keystroke.getKeyCode();
        if (c >= 'a' && c <= 'z') keycode -= 32;
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0), KeyEvent.KEY_PRESSED, System.currentTimeMillis() + 33,
                keystroke.getModifiers(), keystroke.getKeyCode(), keystroke.getKeyChar(), KeyEvent.KEY_LOCATION_STANDARD));
        MethodContext.sleep(Random.nextInt(60, 100));
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0), KeyEvent.KEY_RELEASED, System.currentTimeMillis() + 33,
                keystroke.getModifiers(), keystroke.getKeyCode(), keystroke.getKeyChar(), KeyEvent.KEY_LOCATION_STANDARD));
        if (!(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN)) {
            instance.getApplet().getComponent(0).dispatchEvent(generateKeyEvent(c, KeyEvent.KEY_TYPED, 33));
        }
        MethodContext.sleep(Random.nextInt(70, 130));
    }

    public synchronized void pressKey(int key) {
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0),
                KeyEvent.KEY_PRESSED, System.currentTimeMillis() + 33,
                0, key , (char) key, KeyEvent.KEY_LOCATION_STANDARD));
        MethodContext.sleep(Random.nextInt(60, 100));
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0),
                KeyEvent.KEY_RELEASED, System.currentTimeMillis() + 33,
                0, key , (char) key, KeyEvent.KEY_LOCATION_STANDARD));
        MethodContext.sleep(Random.nextInt(60, 100));
    }

    public synchronized void holdUntil(int key, Condition condition) {
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0),
                KeyEvent.KEY_PRESSED, System.currentTimeMillis() + 33,
                0, key , (char) key, KeyEvent.KEY_LOCATION_STANDARD));
        MethodContext.sleep(500);
        while(!condition.verify()) {
            instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0),
                    KeyEvent.KEY_PRESSED, System.currentTimeMillis() + 33,
                    0, key , (char) key, KeyEvent.KEY_LOCATION_STANDARD));
            MethodContext.sleep(33);
        }
        instance.getApplet().getComponent(0).dispatchEvent(new KeyEvent(instance.getApplet().getComponent(0),
                KeyEvent.KEY_RELEASED, System.currentTimeMillis() + 33,
                0, key , (char) key, KeyEvent.KEY_LOCATION_STANDARD));
        MethodContext.sleep(Random.nextInt(60, 100));
    }

    private KeyEvent generateKeyEvent(char key, int type, int wait) {
        AWTKeyStroke ks = AWTKeyStroke.getAWTKeyStroke(key);
        return new KeyEvent(instance.getApplet().getComponent(0), type, System.currentTimeMillis() + wait, ks.getModifiers(), ks.getKeyCode(), ks.getKeyChar());
    }

    long lastTyped = System.currentTimeMillis();
    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println(System.currentTimeMillis() - lastTyped);
        lastTyped = System.currentTimeMillis();
    }

    long lastPressed = System.currentTimeMillis();
    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println(System.currentTimeMillis() - lastPressed);
        lastPressed = System.currentTimeMillis();
    }

    long lastReleased = System.currentTimeMillis();
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(System.currentTimeMillis() - lastPressed);
        lastReleased = System.currentTimeMillis();
    }
}
