package api.utilities.screen;

import java.awt.*;

/**
 * Imported from https://github.com/Acuity/
 */

public class ScreenLocation {

    private int x, y;

    public ScreenLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static ScreenLocation fromPoint(Point point) {
        return new ScreenLocation(point.x, point.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ScreenLocation increment(int xOff, int yOff) {
        this.x += xOff;
        this.y += yOff;
        return this;
    }

    public ScreenLocation transform(int xOff, int yOff) {
        return new ScreenLocation(x + xOff, y + yOff);
    }

    public int[] toArray() {
        return new int[]{x, y};
    }

    public Point toPoint() {
        return new Point(x, y);
    }

    public double distanceTo(ScreenLocation location) {
        return toPoint().distance(location.toPoint());
    }
}
