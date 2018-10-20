package api.wrappers;

import client.BotInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 7.1.2018
 * Time: 17.08
 */

public class RSHashtable extends RSWrapper {


    public RSHashtable(BotInstance instance, Object object) {
        super(instance, object);
    }

    public List<RSNode> getBuckets() {
        List<RSNode> list = new ArrayList<>();
        Object[] objects = (Object[]) getRawBuckets();
        for (Object object : objects) {
            list.add(new RSNode(instance, object));
        }
        return list;
    }

    public RSNode getHead() {
        return new RSNode(instance, instance.getHookLoader().getFieldValue("HashTable.head", object));
    }

    public int getIndex() {
        return (int) instance.getHookLoader().getFieldValue("HashTable.index", object);
    }

    public int getSize() {
        return (int) instance.getHookLoader().getFieldValue("HashTable.size", object);
    }

    public Object getRawBuckets() {
        return instance.getHookLoader().getFieldValue("HashTable.buckets", object);
    }

    public Object getRawCurrent() {
        return instance.getHookLoader().getFieldValue("HashTable.current", object);
    }

}
