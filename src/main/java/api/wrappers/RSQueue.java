package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 17.1.2018
 * Time: 14.34
 */

public class RSQueue extends RSWrapper {


    public RSQueue(BotInstance instance, Object object) {
        super(instance, object);
    }

    public RSCacheNode getCacheNode() {
        return new RSCacheNode(instance, instance.getHookLoader().getFieldValue("Queue.cacheNode", object));
    }
}
