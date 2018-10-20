package api.utilities;

import api.utilities.screen.ScreenLocation;
import api.utilities.screen.geometry.ScreenPolygon;
import org.apache.commons.math3.distribution.GammaDistribution;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Date: 8.1.2018
 * Time: 9.37
 */

/**
 * Imported some methods from https://github.com/Acuity/
 */

public class Random {

    private static final java.util.Random GEN = new java.util.Random();

    private Random(){}

    public static int nextInt() {
        return GEN.nextInt();
    }

    public static int nextInt(int max) {
        return GEN.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        if (min > max) {
            min ^= max;
            max = min ^ max;
            min ^= max;
        }
        return min + nextInt(max - min);
    }

    public static double nextDouble() {
        return GEN.nextDouble();
    }

    public static double nextDouble(double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (min == max) {
            return min;
        }
        return min + nextDouble() * (max - min);
    }

    public static double nextGammaRandom(double shape, double scale) {
      return new GammaDistribution(shape,  scale).sample();
    }

    public static int nextGaussian(int max) {
        return nextGaussian(0, max);
    }

    public static int nextGaussian(double max) {
        return nextGaussian(0, (int) max);
    }

    public static int nextGaussian(double min, double max) {
        return nextGaussian((int) min, (int) max);
    }

    //including min, excluding max
    public static int nextGaussian(int min, int max) {
        return nextGaussian(min, max, max - min);
    }

    public static int nextGaussian(int min, int max, int range) {
        while (true) {
            int result = (int) (((new java.util.Random().nextGaussian() + 2) / 4) * range);
            if (result >= 0 && min + result < max) {
                return min + result;
            }
        }
    }

    public static boolean nextBoolean() {
        return GEN.nextBoolean();
    }

    public static <T> T nextElement(T[] elements) {
        if (elements == null || elements.length == 0){
            return null;
        }

        if (elements.length == 1){
            return elements[0];
        }
        return elements[nextInt(elements.length - 1)];
    }

    @SuppressWarnings("unchecked")
    public static <T> T nextElement(List<T> elements) {
        if (elements == null || elements.size() == 0){
            return null;
        }

        if (elements.size() == 1){
            return elements.get(0);
        }
        return elements.get(nextInt(elements.size() - 1));
    }

    public static double nextGaussian() {
        return GEN.nextGaussian();
    }

    public static double mid(double min, double max) {
        double r = max - min;
        double m = r / 2;
        double s = Random.nextDouble(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + (sign * (m - Math.sqrt(m * m - s * s)));
    }

    public static int mid(int min, int max) {
        int r = max - min;
        int m = r / 2;
        int s = Random.nextInt(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return (int) (min + m + (sign * (m - Math.sqrt(m * m - s * s))));
    }

    public static int polar(int min, int max) {
        int r = max - min;
        int m = r / 2;
        int s = Random.nextInt(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + (int) (sign * Math.sqrt(m * m - s * s));
    }

    public static double polar(double min, double max) {
        double r = max - min;
        double m = r / 2;
        double s = Random.nextDouble(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + sign * Math.sqrt(m * m - s * s);
    }

    public static int high(int min, int max) {
        int r = max - min;
        int s = Random.nextInt(0, r);
        return (int) (min + Math.sqrt(r * r - s * s));
    }

    public static double high(double min, double max) {
        double r = max - min;
        double s = Random.nextDouble(0, r);
        return (min + Math.sqrt(r * r - s * s));
    }

    public static int low(int min, int max) {
        int r = max - min;
        int s = Random.nextInt(0, r);
        return (int) (max - Math.sqrt(r * r - s * s));
    }

    public static double low(double min, double max) {
        double r = max - min;
        double s = Random.nextDouble(0, r);
        return (max - Math.sqrt(r * r - s * s));
    }

    public static ScreenLocation nextLocation(ScreenPolygon region){
        Rectangle r = region.toPolygon().getBounds();
        int x, y;
        int attempts = 0;
        do {
            x = (int) nextDouble(r.getX(), r.getX() + r.getWidth());
            y = (int) nextDouble(r.getY(), r.getY() + r.getHeight());
            attempts++;
        } while(!region.contains(x, y) && attempts < 1000);
        return new ScreenLocation(x, y);
    }

    public static <T extends java.util.List> T shuffle(T t){
        Collections.shuffle(t);
        return t;
    }

    public static Point mid(Rectangle r) {
        return mid(r.x, r.y, r.width, r.height);
    }

    public static Point mid(int x, int y, int w, int h) {
        int rx = x + mid(0, w);
        int ry = y + mid(0, h);
        return new Point(rx, ry);
    }








    public static void main(String[] args) {
        for (int i = 0; i< 10000; i++) {
            System.out.println(low(500, 10000));
        }
    }
}
