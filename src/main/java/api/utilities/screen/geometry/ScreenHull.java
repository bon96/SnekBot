package api.utilities.screen.geometry;

import api.utilities.screen.ScreenLocation;

import java.util.Collection;

/**
 * Imported from https://github.com/Acuity/
 */

public class ScreenHull extends ScreenPolygon {


    private double coverage = 1;

    public ScreenHull(ScreenLocation... locations) {
        super(locations);
    }

    public ScreenHull(Collection<ScreenLocation> locations) {
        super(locations);
    }

    public double getCoverage() {
        return coverage;
    }

    public ScreenHull setCoverage(double coverage) {
        this.coverage = coverage;
        return this;
    }
}
