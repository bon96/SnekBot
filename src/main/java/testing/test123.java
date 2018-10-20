package testing;

import api.AbstractScript;
import api.methods.NPC;
import api.wrappers.RSGameObject;
import client.loader.RSAppletLoader;

import java.awt.*;
import java.util.List;

/**
 * BonBom
 * Date: 12.1.2018
 * Time: 14.30
 */

public class test123 extends AbstractScript {
    @Override
    public void onStart() {

    }

    @Override
    public int onLoop() {
        if (handleLogin("Nakraft@gmail.com", "3c7h3u")) {
            for(RSGameObject reference : getClient().getRegionReference().getGameObjects()) {
                System.out.println(getInstance().getHookLoader().getFieldValue("ObjectComposite.name", reference.getObject()));
            }
        }
        return 1000;
    }

    @Override
    public void onPaint(Graphics g) {

    }

    public NPC getClosestFishingSpot() {
        List<NPC> npcs = getNpcs().getNpcs();
        NPC closest = null;
        for (NPC npc : npcs) {
            if (npc != null && npc.getX() != 3246 && npc.getY() != 3157) {
                if (npc.getName().equals("Fishing spot")) {
                    if (closest != null) {
                        if (getLocalPlayer().distance(npc) < getLocalPlayer().distance(closest)) {
                            closest = npc;
                        }
                    } else {
                        closest = npc;
                    }
                }
            }
        }
        return closest;
    }

    @Override
    public void onExit() {

    }

    public static void main(String[] args) {
        RSAppletLoader.WORLD = 8;
        new test123();
    }
}
