package api.wrappers;

import client.BotInstance;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 12.17
 */

public class RSLocalTile extends RSWrapper {

    public RSLocalTile(BotInstance instance, Object object) {
        super(instance, object);
    }


    /*
    * Reference methods should be used trough a second getter.
    * Getters are in Tile class.
    */


    public int getPlane() {
        return (int) instance.getHookLoader().getFieldValue("Tile.plane", object);
    }

    public int getFineX() {
        return (int) instance.getHookLoader().getFieldValue("Tile.x", object);
    }

    public int getFineY() {
        return (int) instance.getHookLoader().getFieldValue("Tile.y", object);
    }


    public Object getBoundary() {
        return instance.getHookLoader().getFieldValue("Tile.boundary", object);
    }

    public Object getFloor() {
        return instance.getHookLoader().getFieldValue("Tile.floor", object);
    }

    public Object getItemLayer() {
        return instance.getHookLoader().getFieldValue("Tile.itemLayer", object);
    }

    public Object getObjects() {
        return instance.getHookLoader().getFieldValue("Tile.objects", object);
    }

    public Object getWall() {
        return instance.getHookLoader().getFieldValue("Tile.wall", object);
    }
}
