package api.methods;

import api.MethodContext;
import api.utilities.screen.Projection;
import api.utilities.screen.Screen3DLocation;
import api.utilities.screen.ScreenLocation;
import api.utilities.screen.geometry.Screen3DTriangle;
import api.utilities.screen.geometry.ScreenPolygon;
import api.utilities.screen.geometry.ScreenRectangle;
import api.wrappers.RSModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Imported from https://github.com/Acuity/
 */

public class Model {
    MethodContext context;
    RSModel wrapper;

    private List<Screen3DTriangle> triangles;
    private List<Screen3DLocation> vertices;

    private int fineX, fineY;
    private int orientation;

    public Model(MethodContext context, RSModel wrapper) {
        this.context = context;
        this.wrapper = wrapper;

        int[] xVertices = wrapper.getXVertices();
        int[] yVertices = wrapper.getYVertices();
        int[] zVertices = wrapper.getZVertices();

        vertices = new ArrayList<>(xVertices.length);
        if (xVertices != null && yVertices != null && zVertices != null) {
            for (int i = 0; i < xVertices.length; ++i) {
                vertices.add(new Screen3DLocation(xVertices[i], yVertices[i], zVertices[i]));
            }
        }

        int[] xTriangles = wrapper.getXTriangles();
        int[] yTriangles = wrapper.getYTriangles();
        int[] zTriangles = wrapper.getZTriangles();

        triangles = new ArrayList<>(xTriangles.length);
        for (int i = 0; i < xTriangles.length; ++i) {
            triangles.add(new Screen3DTriangle(vertices.get(xTriangles[i]), vertices.get(yTriangles[i]), vertices.get(zTriangles[i])));
        }
    }

    public List<Screen3DLocation> getVertices() {
        return vertices;
    }

    public List<Screen3DTriangle> getTriangles() {
        return triangles;
    }

    public int getFineX() {
        return fineX;
    }

    public int getFineY() {
        return fineY;
    }

    public Model place(int fineX, int fineY) {
        this.fineX = fineX;
        this.fineY = fineY;
        return this;
    }

    private List<Screen3DTriangle> rotate(List<Screen3DTriangle> triangles, int orientation) {
        List<Screen3DTriangle> rotatedTriangles = new ArrayList<>();
        for (Screen3DTriangle triangle : triangles) {
            Screen3DTriangle rotatedTriangle = new Screen3DTriangle(
                    triangle.getP1().rotate(orientation),
                    triangle.getP2().rotate(orientation),
                    triangle.getP3().rotate(orientation));
            rotatedTriangles.add(rotatedTriangle);
        }
        return rotatedTriangles;
    }

    public Model rotateTo(int orientation) {
        this.orientation = (orientation + 1024) % 2048;
        if (this.orientation != 0) triangles = rotate(triangles, this.orientation);
        return this;
    }

    public List<ScreenPolygon> getPolygons() {
        List<ScreenPolygon> polygons = new ArrayList<>();

        for (Screen3DTriangle triangle : triangles) {
            Screen3DLocation xVertex = triangle.getP1();
            Screen3DLocation yVertex = triangle.getP2();
            Screen3DLocation zVertex = triangle.getP3();

            ScreenLocation x = Projection.fineToScreen(context, fineX - xVertex.getX(), fineY - xVertex.getZ(), -xVertex.getY());
            ScreenLocation y = Projection.fineToScreen(context, fineX - yVertex.getX(), fineY - yVertex.getZ(), -yVertex.getY());
            ScreenLocation z = Projection.fineToScreen(context, fineX - zVertex.getX(), fineY - zVertex.getZ(), -zVertex.getY());

            if (x.getX() == -1 || y.getX() == -1 || z.getX() == -1) continue;
            polygons.add(new ScreenPolygon(x, y, z));
        }

        return polygons;
    }

    private Point getMax() {
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;

        for (ScreenPolygon polygon : getPolygons()) {
            for (ScreenLocation location : polygon.getLocations()) {
                if (location.getX() > x) {
                    x = location.getX();
                }
                if (location.getY() > y) {
                    y = location.getY();
                }
            }
        }
        return new Point(x, y);
    }

    private Point getMin() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (ScreenPolygon polygon : getPolygons()) {
            for (ScreenLocation location : polygon.getLocations()) {
                if (location.getX() < x) {
                    x = location.getX();
                }
                if (location.getY() < y) {
                    y = location.getY();
                }
            }
        }
        return new Point(x, y);
    }

    public ScreenRectangle getScreenRectangle() {
        Point min = getMin();
        Point max = getMax();
        return new ScreenRectangle(new ScreenLocation(min.x, min.y), max.x - min.x, max.y - min.y);
    }


    public RSModel getWrapper() {
        return wrapper;
    }



}
