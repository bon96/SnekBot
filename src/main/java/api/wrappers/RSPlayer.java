package api.wrappers;

import api.wrappers.RSActor;
import client.BotInstance;

/**
 * BonBom
 * Date: 3.1.2018
 * Time: 18.01
 */

public  class RSPlayer extends RSActor {

    public RSPlayer(BotInstance instance, Object object) {
        super(instance, object);
    }


    public String getName() {
        return (String) instance.getHookLoader().getFieldValue("Player.name", object);
    }

    public int getLevel() {
        return (int) instance.getHookLoader().getFieldValue("Player.level", object);
    }

    public Object getComposite() {
        return instance.getHookLoader().getFieldValue("Player.composite", object);
    }

    public boolean getHidden() {
        return (boolean) instance.getHookLoader().getFieldValue("Player.hidden", object);
    }


    public Object getModel() {
        return instance.getHookLoader().getFieldValue("Player.model", object);
    }

    public int getOverHeadIcon() {
        return (int) instance.getHookLoader().getFieldValue("Player.overheadIcon", object);
    }

    public int getSkullIcon() {
        return (int) instance.getHookLoader().getFieldValue("Player.skullIcon", object);
    }

    public boolean getStandingStill() {
        return (boolean) instance.getHookLoader().getFieldValue("Player.standingStill", object);
    }

    public int getTeam() {
        return (int) instance.getHookLoader().getFieldValue("Player.team", object);
    }
}
