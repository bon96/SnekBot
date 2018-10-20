package api.wrappers;

import api.wrappers.RSActor;
import api.wrappers.RSNpcComposite;
import client.BotInstance;

/**
 * BonBom
 * Date: 8.1.2018
 * Time: 18.17
 */

public class RSNPC extends RSActor {

    public RSNPC(BotInstance instance, Object object) {
        super(instance, object);
    }



    public RSNpcComposite getComposite() {
        return new RSNpcComposite(instance, instance.getHookLoader().getFieldValue("Npc.composite", object));
    }
}
