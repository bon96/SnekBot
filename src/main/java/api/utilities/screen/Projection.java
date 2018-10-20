package api.utilities.screen;

import api.MethodContext;
import api.utilities.screen.geometry.ScreenRectangle;

import java.awt.*;


/**
 * Imported from https://github.com/Acuity/
 */

public class Projection {

    public static final ScreenRectangle GAME_SCREEN = new ScreenRectangle(new ScreenLocation(4, 4), new ScreenLocation(512, 334));
    public static final Dimension APPLET_SIZE = new Dimension(765, 503);

    public static final int TILE_PIXEL_SIZE = 128;
    public static final int[] SINE = new int[2048];
    public static final int[] COSINE = new int[2048];

    static {
        for (int i = 0; i < 2048; ++i) {
            SINE[i] = (int) (65536.0D * Math.sin((double) i * 0.0030679615D));
            COSINE[i] = (int) (65536.0D * Math.cos((double) i * 0.0030679615D));
        }
    }

    public static ScreenLocation fineToScreen(MethodContext context, int tileX, int tileY, int tileZ) {

        Rectangle viewportRectangle = new Rectangle(5, 5, 511, 333);
        Point point = new Point(-1, -1);
        int pitchSin = SINE[context.getClient().getCameraPitch()];
        int pitchCos = COSINE[context.getClient().getCameraPitch()];
        int yawSin = SINE[context.getClient().getCameraYaw()];
        int yawCos = COSINE[context.getClient().getCameraYaw()];

        int tileHeight = getTileHeight(context, tileX, tileY);
        int tileHeightMinusTileZ = tileHeight - tileZ;
        int tileMinusCameraX = tileX - context.getClient().getCameraX();
        int tileMinusCameraY = tileY - context.getClient().getCameraY();
        int tileMinusCameraZ = tileHeightMinusTileZ - context.getClient().getCameraZ();


        int var1 = tileMinusCameraY * yawCos - tileMinusCameraX * yawSin >> 16;
        int var2 = pitchSin * tileMinusCameraZ + pitchCos * var1 >> 16;
        int var3 = yawSin * tileMinusCameraY + yawCos * tileMinusCameraX >> 16;
        int var4 = pitchCos * tileMinusCameraZ - pitchSin * var1 >> 16;


        int pointX = ((var3 << 9) / var2) + 258;
        int pointY = ((var4 << 9) / var2) + 169;
        if (!viewportRectangle.contains(pointX, pointY)) {
            return new ScreenLocation(point.x, point.y);
        }
        return new ScreenLocation(pointX, pointY);
    }


    public static int getTileHeight(MethodContext context, int x, int y) {
        int x1 = x >> 7;
        int y1 = y >> 7;

        byte[][][] settings = (byte[][][]) context.getClient().getTileSettingsReference();
        int[][][] heights = (int[][][]) context.getClient().getTileHeightsReference();
        int plane = context.getClient().getPlane();
        if (plane < 3 && (settings[1][x1][y1] & 0x2) == 2) {
            plane++;
        }
        int x2 = x & 0x7F;
        int y2 = y & 0x7F;
        int h1 = heights[plane][x1][y1] * (128 - x2) + heights[plane][x1 + 1][y1] * x2 >> 7;
        int h2 = heights[plane][x1][y1 + 1] * (128 - x2) + heights[plane][x1 + 1][y1 + 1] * x2 >> 7;
        return h1 * (128 - y2) + h2 * y2 >> 7;
    }
}
