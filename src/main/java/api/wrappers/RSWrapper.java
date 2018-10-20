package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 3.2.2018
 * Time: 16.30
 */

public class RSWrapper {
    BotInstance instance;
    Object object;

    public RSWrapper(BotInstance instance, Object object) {
        this.instance = instance;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
