package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 17.1.2018
 * Time: 15.21
 */

public class RSPlayerComposite extends RSWrapper {


    public RSPlayerComposite(BotInstance instance, Object object) {
        super(instance, object);
    }

    public long getAnimatedModelID() {
        return (long) instance.getHookLoader().getFieldValue("PlayerComposite.animatedModelID", object);
    }

    public Object getAppearance() {
        return instance.getHookLoader().getFieldValue("PlayerComposite.appearance", object);
    }

    public Object getBodyColors() {
        return instance.getHookLoader().getFieldValue("PlayerComposite.bodyColors", object);
    }

    public Object getFemale() {
        return instance.getHookLoader().getFieldValue("PlayerComposite.female", object);
    }

    public int getNpcID() {
        return (int) instance.getHookLoader().getFieldValue("PlayerComposite.npcID", object);
    }

    public long getStaticModelID() {
        return (long) instance.getHookLoader().getFieldValue("PlayerComposite.staticModelID", object);
    }
}
