package api.methods;

import java.awt.*;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 15.04
 */

public class Tile {
    int x;
    int y;
    int z;


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Tile(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

    public double distance(Tile tile) {
        Point point1 = new Point(this.getX(), this.getY());
        Point point2 = new Point(tile.getX(), tile.getY());

        return point1.distance(point2);
    }
}
