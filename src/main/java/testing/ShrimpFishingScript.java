package testing;

import api.AbstractScript;
import api.input.Mouse;
import api.methods.NPC;
import api.utilities.Random;
import client.loader.RSAppletLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 5.1.2018
 * Time: 12.50
 */

public class ShrimpFishingScript extends AbstractScript {

    @Override
    public void onStart() {
    }
//"kile1155:kile1155@gmail.com", "0f16wu"
    @Override
    public int onLoop() {
        if (handleLogin("Bevanda@gmail.com", "pa1hv9")) {
            if (getInventory().isItemSelected()) {
                getInventory().deselectItem();
            } else {
                if (!getInventory().isFull()) {
                    if (getLocalPlayer().getAnimation() == -1) {
                        NPC spot = getLeastCostFishingSpot();
                        if (spot != null) {
                            if (spot.interact("Net")) {
                                sleepUntil(() -> getLocalPlayer().getAnimation() != -1, Random.nextInt(4000, 8000));
                            }
                        }
                    }
                } else {
                    getInventory().dropAllInRange(1, 28);
                    return Random.nextInt(50, 100);
                }
            }
        }
        return Random.low(1000, 20000);
    }

    public NPC getLeastCostFishingSpot() {
        List<NPC> npcs = getNpcs().getNpcs();
        List<NPC> fishingSpotsOnScreen = new ArrayList<>();
        for (NPC npc : npcs) {
            if (npc != null && npc.getX() != 3246 && npc.getY() != 3157 && npc.getName().equals("Fishing spot") && npc.isOnScreen()) {
                fishingSpotsOnScreen.add(npc);
            }
        }
        NPC closest = null;
        if (!fishingSpotsOnScreen.isEmpty()) {
            for (NPC npc : fishingSpotsOnScreen) {
                if (closest != null) {
                    if (Mouse.point.distance(npc.getSuitablePoint()) < Mouse.point.distance(closest.getSuitablePoint())) {
                        closest = npc;
                    }
                } else {
                    closest = npc;
                }
            }
            if (closest != null) {
                System.out.println("Returning leastcost");
                return closest;
            }
        } else {
            System.out.println("spots on screen is empty");
        }
        System.out.println("Returning closest");
        return getClosestFishingSpot();
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
        //18, 34, 49
    }

    @Override
    public  void onPaint(Graphics g) {

    }

    public static void main(String[] args) {
        RSAppletLoader.WORLD = 8;
        new ShrimpFishingScript();
    }
}
