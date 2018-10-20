package api.wrappers;

import client.BotInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 25.1.2018
 * Time: 12.48
 */

public class RSRegion extends RSWrapper {


    public RSRegion(BotInstance instance, Object object) {
        super(instance, object);
    }

    public List<RSGameObject> getGameObjects() {
        List<RSGameObject> list = new ArrayList<>();
        for(Object object : getRawGameObjects()) {
            if(object != null) {
                list.add(new RSGameObject(instance, object));
            }
        }
        return list;
    }

    public List<RSLocalTile> getTiles() {
        List<RSLocalTile> list = new ArrayList<>();

        for(Object[][] object0 : getRawTiles()) {
            for(Object[] object1 : object0) {
                for(Object object2 : object1) {
                    if(object2 != null) {
                        list.add(new RSLocalTile(instance, object2));
                    }
                }
            }
        }
        return list;
    }

    public Object getFocusedX() {
        return instance.getHookLoader().getFieldValue("Region.focusedX", object);
    }

    public Object getFocusedY() {
        return instance.getHookLoader().getFieldValue("Region.focusedY", object);
    }

    public Object[] getRawGameObjects() {
        return (Object[]) instance.getHookLoader().getFieldValue("Region.gameObjects", object);
    }

    public Object getRightClickWalk() {
        return instance.getHookLoader().getFieldValue("Region.rightClickWalk", object);
    }

    public Object[][][] getRawTiles() {
        return (Object[][][]) instance.getHookLoader().getFieldValue("Region.tiles", object);
    }

    public Object getVisibilityTilesMap() {
        return instance.getHookLoader().getFieldValue("Region.visibilityTilesMap", object);
    }

    public Object getVisibilityTiles() {
        return instance.getHookLoader().getFieldValue("Region.visibilityTiles", object);
    }
}
