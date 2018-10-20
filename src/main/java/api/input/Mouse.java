package api.input;

import api.utilities.Random;
import api.utilities.screen.ScreenLocation;
import client.BotInstance;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 
 * Date: 4.1.2018
 * Time: 12.28
 */

public class Mouse implements MouseMotionListener, MouseListener {
    public static BotInstance instance;
    public static Point point = new Point(0, 0);
    public static boolean isMouseOnScreen = true;

    private double speed;

    public double speedMultiplier = 10D; //15
    public double speedAddition = 15D; // 15
    public double speedDivisor = 10D; // 10

    public double gravity = 7D; // 9
    public double wind = 2D; // 3
    public double minWaitBase = 5D; // 5
    public double maxWaitBase = 10D; // 10
    public double maxStepBase = 10D; // 10
    public double targetAreaBase = 15D; // 9


    public Mouse(BotInstance instance) {
        this.instance = instance;
    }

    public boolean click(ScreenLocation screenLocation) {
        return click(screenLocation.toPoint());
    }

    public boolean click(Point point) {
        return click(point.x, point.y);
    }

    public boolean click(int x, int y) {
        return click(x, y, ClickMode.LEFT_BUTTON);
    }

    public boolean click(ScreenLocation screenLocation, int button) {
        return click(screenLocation.toPoint(), button);
    }

    public boolean click(Point point, int button) {
        return click(point.x, point.y, button);
    }

    public boolean click(int x, int y, int button) {
        if (move(x, y)) {
            click(button);
            return true;
        }
        return false;
    }

    public boolean move(ScreenLocation screenLocation) {
        return move(screenLocation.toPoint());
    }

    public boolean move(Point point) {
        return move((int)point.getX(), (int)point.getY());
    }

    public boolean move(int x, int y) {
        if (point == null) {
            point = new Point(0, 0);
        }
        if (point.getX() != x || point.getY() != y) {
            windMouse(x, y);
        }
        return true;
    }

    private void moveStraight(int x, int y) {
        if (!isMouseOnScreen) {
            instance.getCanvas().dispatchEvent(new MouseEvent(
                    instance.getCanvas(), MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, x, y, 0, false));
        isMouseOnScreen = true;
        }
        instance.getCanvas().dispatchEvent(new MouseEvent(
                instance.getCanvas(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false
        ));
        point.setLocation(x, y);
    }

    public Point windMouse(int x, int y) {
        speed = (Random.low(0D, 1D) * speedMultiplier + speedAddition) / speedDivisor;
        return windMouseImpl(point.getX(), point.getY(), x, y, gravity, wind, minWaitBase / speed, maxWaitBase / speed, maxStepBase * speed, targetAreaBase * speed);
    }

    private Point windMouseImpl(double xs, double ys, double xe, double ye, double gravity, double wind, double minWait, double maxWait, double maxStep, double targetArea) {
        final double sqrt3 = Math.sqrt(3);
        final double sqrt5 = Math.sqrt(5);


        double dist, veloX = 0, veloY = 0, windX = 0, windY = 0;
        while ((dist = Math.hypot(xs - xe, ys - ye)) >= 1) {
            wind = Math.min(wind, dist);
            if (dist >= targetArea) {
                windX = windX / sqrt3 + (Math.random() * (wind * 2D + 1D) - wind) / sqrt5;
                windY = windY / sqrt3 + (Math.random() * (wind * 2D + 1D) - wind) / sqrt5;
            } else {
                windX /= sqrt3;
                windY /= sqrt3;
                if (maxStep < 3) {
                    maxStep = Math.random() * 3 + 3D;
                } else {
                    maxStep /= sqrt5;
                }
            }
            veloX += windX + gravity * (xe - xs) / dist;
            veloY += windY + gravity * (ye - ys) / dist;
            double veloMag = Math.hypot(veloX, veloY);
            if (veloMag > maxStep) {
                double randomDist = maxStep / 2D + Math.random() * maxStep / 2D;
                veloX = (veloX / veloMag) * randomDist;
                veloY = (veloY / veloMag) * randomDist;
            }
            xs += veloX;
            ys += veloY;
            int mx = (int) Math.round(xs);
            int my = (int) Math.round(ys);
            if ((int) Math.round(point.getX()) != mx || (int) Math.round(point.getY()) != my) {
                moveStraight(mx, my);
            }
            double step = Math.hypot(xs - point.getX(), ys - point.getY());
            try {
                Thread.sleep(Math.round((maxWait - minWait) * (step / maxStep) + minWait));
            } catch (InterruptedException ex) {
            }
        }
        return new Point((int) Math.round(point.getX()), (int) Math.round(point.getY()));
    }





    public synchronized void click(int button) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        int btnMask = (button == 1 ? MouseEvent.BUTTON1_DOWN_MASK : 0) | (button == 2 ? (MouseEvent.BUTTON2_DOWN_MASK | MouseEvent.META_DOWN_MASK) : 0) | (button == 3 ? (MouseEvent.BUTTON3_DOWN_MASK | MouseEvent.META_DOWN_MASK) : 0);
        int btn = 0;
        switch (button) {
            case 1:
                btn = MouseEvent.BUTTON1;
                break;
            case 2:
                btn = MouseEvent.BUTTON2;
                break;
            case 3:
                btn = MouseEvent.BUTTON3;
                break;
        }
        MouseEvent a = new MouseEvent(instance.getApplet(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), btnMask, (int) point.getX(), (int) point.getY(), 1, false, btn);
        instance.getApplet().getComponent(0).dispatchEvent(a);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        long time = System.currentTimeMillis();
        a = new MouseEvent(instance.getApplet(), MouseEvent.MOUSE_RELEASED, time, btnMask, (int) point.getX(), (int) point.getY(), 1, false, btn);
        instance.getApplet().getComponent(0).dispatchEvent(a);
        a = new MouseEvent(instance.getApplet(), MouseEvent.MOUSE_CLICKED, time, btnMask, (int) point.getX(), (int) point.getY(), 1, false, btn);
        instance.getApplet().getComponent(0).dispatchEvent(a);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        point.setLocation(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        point.setLocation(e.getPoint());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isMouseOnScreen = true;
        System.out.println("mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isMouseOnScreen = false;
        System.out.println("mouse exited");
    }
}
