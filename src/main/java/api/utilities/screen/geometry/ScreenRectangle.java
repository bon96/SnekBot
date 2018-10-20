package api.utilities.screen.geometry;

import api.utilities.Random;
import api.utilities.screen.ScreenLocation;

/**
 * Imported from https://github.com/Acuity/
 */

public class ScreenRectangle  extends ScreenPolygon {

    private final ScreenLocation root;
    private final int width;
    private final int height;

    public ScreenRectangle(ScreenLocation root, int width, int height) {
        super(convert(root, width, height));
        this.root = root;
        this.width = width;
        this.height = height;
    }

    public ScreenRectangle(int x, int y, int width, int height){
        this(new ScreenLocation(x, y), width, height);
    }

    public ScreenRectangle(ScreenLocation low, ScreenLocation high) {
        this(low, high.getX() - low.getX(), high.getY() - low.getY());
    }

    private static ScreenLocation[] convert(ScreenLocation p1, int width, int height) {
        ScreenLocation p2 = p1.transform(width, 0);
        ScreenLocation p3 = p2.transform(0, height);
        ScreenLocation p4 = p3.transform(-width, 0);
        return new ScreenLocation[]{p1, p2, p3, p4};
    }

    public ScreenLocation getGaussianPoint() {
        int x = root.getX() + Random.nextGaussian(0, getWidth());
        int y = root.getY() + Random.nextGaussian(0, getHeight());
        return new ScreenLocation(x, y);
    }

    public ScreenLocation getRoot() {
        return root;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
