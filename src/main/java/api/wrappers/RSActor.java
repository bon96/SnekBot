package api.wrappers;

import api.wrappers.RSRenderable;
import client.BotInstance;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 11.05
 */

public class RSActor extends RSRenderable {

    public RSActor(BotInstance instance, Object object) {
        super(instance, object);
    }

    public int getFineX() {
        return (int) instance.getHookLoader().getFieldValue("Actor.localX", object);
    }

    public int getFineY() {
        return (int) instance.getHookLoader().getFieldValue("Actor.localY", object);
    }

    public int getAnimation() {
        return (int) instance.getHookLoader().getFieldValue("Actor.animation", object);
    }

    public int getAnimationDelay() {
        return (int) instance.getHookLoader().getFieldValue("Actor.animationDelay", object);
    }

    public int getCombatTime() {
        return (int) instance.getHookLoader().getFieldValue("Actor.combatTime", object);
    }

    public int getStandAnimation() {
        return (int) instance.getHookLoader().getFieldValue("Actor.standAnimation", object);
    }

    public int getFrameOne() {
        return (int) instance.getHookLoader().getFieldValue("Actor.frameOne", object);
    }

    public int getFrameTwo() {
        return (int) instance.getHookLoader().getFieldValue("Actor.frameTwo", object);
    }

    public Object getHealthBars() {
        return instance.getHookLoader().getFieldValue("Actor.healthBars", object);
    }

    public Object getHitCycles() {
        return instance.getHookLoader().getFieldValue("Actor.hitCycles", object);
    }

    public Object getHitDamages() {
        return instance.getHookLoader().getFieldValue("Actor.hitDamages", object);
    }

    public Object getHitTypes() {
        return instance.getHookLoader().getFieldValue("Actor.hitTypes", object);
    }

    public int getInteracting() {
        return (int) instance.getHookLoader().getFieldValue("Actor.interacting", object);
    }

    public Object getMessage() {
        return instance.getHookLoader().getFieldValue("Actor.message", object);
    }

    public int getOrientation() {
        return (int) instance.getHookLoader().getFieldValue("Actor.orientation", object);
    }

    public int getQueueSize() {
        return (int) instance.getHookLoader().getFieldValue("Actor.queueSize", object);
    }

    public Object getQueueTraversed() {
        return instance.getHookLoader().getFieldValue("Actor.queueTraversed", object);
    }

    public Object getQueueX() {
        return instance.getHookLoader().getFieldValue("Actor.queueX", object);
    }

    public Object getQueueY() {
        return instance.getHookLoader().getFieldValue("Actor.queueY", object);
    }

    public int getRuntimeAnimation() {
        return (int) instance.getHookLoader().getFieldValue("Actor.runtimeAnimation", object);
    }

}
