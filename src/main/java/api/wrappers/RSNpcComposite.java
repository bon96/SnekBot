package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 8.1.2018
 * Time: 18.17
 */

public class RSNpcComposite extends RSWrapper {


    public RSNpcComposite(BotInstance instance, Object object) {
        super(instance, object);
    }

    public String[] getActions() {
        return (String[]) instance.getHookLoader().getFieldValue("NpcComposite.actions", object);
    }

    public int getID() {
        return (int) instance.getHookLoader().getFieldValue("NpcComposite.id", object);
    }

    public String getName() {
        return (String) instance.getHookLoader().getFieldValue("NpcComposite.name", object);
    }
}
