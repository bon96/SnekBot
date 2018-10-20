package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 7.1.2018
 * Time: 17.19
 */

public class RSNode extends RSWrapper {


    public RSNode(BotInstance instance, Object object) {
        super(instance, object);
    }

    public Object getObject() {
        return object;
    }

    public long getID() {
        return (long) instance.getHookLoader().getFieldValue("Node.id", object);
    }

    public RSNode getNext() {
        return new RSNode(instance, instance.getHookLoader().getFieldValue("Node.next", object));
    }

    public RSNode getPrevious() {
        return new RSNode(instance, instance.getHookLoader().getFieldValue("Node.previous", object));
    }
}
