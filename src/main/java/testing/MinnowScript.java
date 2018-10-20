package testing;

/**
 * BonBom
 * Date: 2.2.2018
 * Time: 20.40
 */

import api.AbstractScript;
import api.input.Mouse;
import api.methods.NPC;
import api.methods.Tile;
import api.utilities.Random;
import client.loader.RSAppletLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 5.1.2018
 * Time: 12.50
 */

public class MinnowScript extends AbstractScript {
    NPC spot;
    Tile lastTile;

    @Override
    public void onStart() {
    }

    @Override
    public int onLoop() {
        if (handleLogin("Narbooj@gmail.com", "i008sw")) {
            if(getCamera().getCameraPitch() < 180) {
                getCamera().rotatetoPitch(Random.nextInt(190, 270));
            }
            if (getInventory().isItemSelected()) {
                getInventory().deselectItem();
            } else {
                if(spot == null || spot.getName() == null) {
                    spot = getClosestFishingSpot();
                } else {
                    if (lastTile == null || (spot.getTile().getX() != lastTile.getX() || spot.getY() != lastTile.getY()) || getLocalPlayer().getAnimation() != 621) {
                        if(spot.interact("Net")) {
                            sleepUntil(() -> spot.distance(getLocalPlayer()) <= 1 && getLocalPlayer().getAnimation() == 621, Random.nextInt(5000, 7000));
                            lastTile = spot.getTile();
                            System.out.println("Fishing");
                        }
                    }
                }
            }
        }
        return Random.low(200, 4000);
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
                if (closest != null && npc.getModel() != null) {
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

    public static void main(String[] args) {
        RSAppletLoader.WORLD = 10;
        new MinnowScript();
    }
}
