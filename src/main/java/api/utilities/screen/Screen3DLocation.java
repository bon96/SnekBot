package api.utilities.screen;

import api.MethodContext;

/**
 * Imported from https://github.com/Acuity/
 */
public class Screen3DLocation {

    private int x, y, z;

    public Screen3DLocation(int x, int y, int z) {
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

    public ScreenLocation toScreenLocation(MethodContext context) {
        return Projection.fineToScreen(context, x, y, z);
    }

    public Screen3DLocation rotate(int orientation) {
        int sin = Projection.SINE[orientation];
        int cos = Projection.COSINE[orientation];
        return new Screen3DLocation(x * cos + z * sin >> 16, y, z * cos - x * sin >> 16);
    }

    public int[] toArray() {
        return new int[]{x, y, z};
    }


}
