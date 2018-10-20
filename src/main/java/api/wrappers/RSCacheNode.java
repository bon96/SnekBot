package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 11.1.2018
 * Time: 16.23
 */

public class RSCacheNode extends RSNode {

    public RSCacheNode(BotInstance instance, Object object) {
        super(instance, object);
    }

    public RSCacheNode getNextCached() {
        return new RSCacheNode(instance, instance.getHookLoader().getFieldValue("CacheNode.next", object));
    }

    public RSCacheNode getPreviousCached() {
        return new RSCacheNode(instance, instance.getHookLoader().getFieldValue("CacheNode.previous", object));
    }
}
