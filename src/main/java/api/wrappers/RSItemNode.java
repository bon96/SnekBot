package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 7.1.2018
 * Time: 16.56
 */

public class RSItemNode extends RSWrapper {


    public RSItemNode(BotInstance instance, Object object) {
        super(instance, object);
    }

    public Object getIds() {
        return instance.getHookLoader().getFieldValue("ItemNode.ids", object);
    }

    public Object getStackSizes() {
        return instance.getHookLoader().getFieldValue("ItemNode.stackSizes", object);
    }
}
