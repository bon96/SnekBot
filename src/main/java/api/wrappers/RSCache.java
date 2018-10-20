package api.wrappers;

import client.BotInstance;


/**
 * BonBom
 * Date: 7.1.2018
 * Time: 15.50
 */

public class RSCache extends RSWrapper {


    public RSCache(BotInstance instance, Object object) {
        super(instance, object);
    }

    public RSCacheNode getCacheNode() {
        return new RSCacheNode(instance, instance.getHookLoader().getFieldValue("Cache.cacheNode", object));
    }

    public RSQueue getQueue() {
        return new RSQueue(instance, instance.getHookLoader().getFieldValue("Cache.queue", object));
    }

    public int getRemaining() {
        return (int) instance.getHookLoader().getFieldValue("Cache.remaining", object);
    }

    public RSHashtable getHashTable() {
        return new RSHashtable(instance, instance.getHookLoader().getFieldValue("Cache.hashTable", object));
    }

    public int getSize() {
        return (int) instance.getHookLoader().getFieldValue("Cache.size", object);
    }

}
