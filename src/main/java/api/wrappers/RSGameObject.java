package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 18.48
 */

public class RSGameObject extends RSWrapper {


    public RSGameObject(BotInstance instance, Object object) {
        super(instance, object);
    }

    public int getFlags() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.flags", object);
    }

    public int getHeight() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.height", object);
    }

    //>> 14 & 0x7FFF
    public int getRawID() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.id", object);
    }

    public int getOffsetX() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.offsetX", object);
    }

    public int getOffsetY() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.offsetY", object);
    }

    public int getOrientation() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.orientation", object);
    }

    public int getPlane() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.plane", object);
    }

    public int getRelativeX() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.relativeX", object);
    }

    public int getRelativeY() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.relativeY", object);
    }

    public Object getRenderable() {
        return instance.getHookLoader().getFieldValue("GameObject.renderable", object);
    }

    public int getX() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.x", object);
    }

    public int getY() {
        return (int) instance.getHookLoader().getFieldValue("GameObject.y", object);
    }
}
