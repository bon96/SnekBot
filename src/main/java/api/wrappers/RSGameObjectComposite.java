package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 2.2.2018
 * Time: 14.36
 */

public class RSGameObjectComposite extends RSWrapper {


    public RSGameObjectComposite(BotInstance instance, Object object) {
        super(instance, object);
    }

    public Object getActions() {
        return instance.getHookLoader().getFieldValue("ObjectComposite.actions", object);
    }

    public Object getName() {
        return instance.getHookLoader().getFieldValue("ObjectComposite.name", object);
    }

}
