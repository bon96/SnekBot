package api.utilities.screen.geometry;

import api.utilities.Random;
import api.utilities.screen.ScreenLocation;

import java.awt.*;
import java.util.Collection;

/**
 * Imported from https://github.com/Acuity/
 */

public class ScreenPolygon {
    private ScreenLocation[] locations;
    private Polygon polygon;

    public ScreenPolygon(ScreenLocation... locations) {
        this.locations = locations;
    }

    public ScreenPolygon(Collection<ScreenLocation> locations) {
        this.locations = locations.toArray(new ScreenLocation[locations.size()]);
    }

    public ScreenLocation[] getLocations() {
        return locations;
    }

    public ScreenLocation randomLocation(){
        return Random.nextLocation(this);
    }

    public Polygon toPolygon(){
        if (polygon == null){
            polygon = new Polygon();
            for (ScreenLocation location : locations) {
                polygon.addPoint(location.getX(), location.getY());
            }
        }
        return polygon;
    }

    public Rectangle getRectangle() {
        return this.toPolygon().getBounds();
    }


    public boolean contains(ScreenLocation location){
        return contains(location.getX(), location.getY());
    }

    public boolean contains(int x, int y) {
        return toPolygon().contains(x, y);
    }
}
