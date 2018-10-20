package api.wrappers;

import api.wrappers.RSCacheNode;
import client.BotInstance;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 10.43
 */

public class RSRenderable extends RSCacheNode {


    public RSRenderable(BotInstance instance, Object object) {
        super(instance, object);
    }

    public int getModelHeight() {
        return (int) instance.getHookLoader().getFieldValue("Renderable.modelHeight", object);
    }


}
