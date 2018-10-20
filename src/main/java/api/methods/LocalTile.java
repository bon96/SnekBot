package api.methods;

import api.MethodContext;
import api.wrappers.RSLocalTile;


/**
 * BonBom
 * Date: 10.1.2018
 * Time: 12.28
 */

public class LocalTile {
    MethodContext context;
    RSLocalTile wrapper;


    public LocalTile(MethodContext context, RSLocalTile wrapper) {
        this.context = context;
        this.wrapper = wrapper;
    }

    public int getX() {
        return (wrapper.getFineX() >> 7) + context.getClient().getBaseX();
    }

    public int getY() {
        return (wrapper.getFineY() >> 7) + context.getClient().getBaseY();
    }

    public int getZ() {
        return wrapper.getPlane();
    }

    public int getFineX() {
        return wrapper.getFineX();
    }

    public int getFineY() {
        return wrapper.getFineY();
    }


    public RSLocalTile getWrapper() {
        return wrapper;
    }
}
