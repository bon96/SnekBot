package api.methods;

import api.MethodContext;
import api.wrappers.RSGameObject;

/**
 * BonBom
 * Date: 2.2.2018
 * Time: 12.43
 */

public class GameObject {
    MethodContext context;
    RSGameObject wrapper;

    public GameObject(MethodContext context, RSGameObject wrapper) {
        this.context = context;
        this.wrapper = wrapper;
    }

    public int getID() {
        return wrapper.getRawID() >> 14 & 0x7FFF;
    }

    public RSGameObject getWrapper() {
        return wrapper;
    }


}
