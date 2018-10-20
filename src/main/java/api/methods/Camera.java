package api.methods;

import api.MethodContext;

import java.awt.event.KeyEvent;

/**
 * BonBom
 * Date: 21.1.2018
 * Time: 17.42
 */

public class Camera {
    MethodContext context;

    public Camera(MethodContext context) {
        this.context = context;
    }

    public void rotateTo(Actor actor) {
        int playerX = context.getLocalPlayer().getX();
        int playerY = context.getLocalPlayer().getY();

        int actorX = actor.getX();
        int actorY = actor.getY();

        int diffX = actorX - playerX;
        int diffY = actorY - playerY;

        double hypotenuse = (int) Math.sqrt((diffX * diffX) + (diffY * diffY));
        double gamma = Math.toDegrees(Math.acos(diffX / hypotenuse));
        double factor = gamma / 180;

        int yaw;
        if (diffY > 0) {
            yaw = (int) (511 + (1023 * factor));
        } else {
            yaw = (int) (511 - (1023 * factor));
            if (yaw < 0) {
                yaw = 2047 + yaw;
            }
        }
        if (yaw + 1024 < 2048) {
            rotateToYaw(yaw + 1024);
        } else {
            rotateToYaw(yaw - 1024);
        }
    }


    public void rotateToYaw(int yaw) {
        // right higher, left lower
        int cameraYaw = getCameraYaw();
        int diff = yaw > cameraYaw ?  yaw - cameraYaw : cameraYaw - yaw;

        if (diff > 1024) {
            if (cameraYaw > 1024) {
                context.getKeyboard().holdUntil(KeyEvent.VK_RIGHT, () -> (getCameraYaw() - yaw) < 20 && (getCameraYaw() - yaw) > -20);
            } else {
                context.getKeyboard().holdUntil(KeyEvent.VK_LEFT, () -> (getCameraYaw() - yaw) < 20 && (getCameraYaw() - yaw) > -20);
            }
        } else {
            if (cameraYaw > yaw) {
                context.getKeyboard().holdUntil(KeyEvent.VK_LEFT, () -> (getCameraYaw() - yaw) < 20 && (getCameraYaw() - yaw) > -20);
            } else {
                context.getKeyboard().holdUntil(KeyEvent.VK_RIGHT, () -> (getCameraYaw() - yaw) < 20 && (getCameraYaw() - yaw) > -20);
            }
        }
    }

    public void rotatetoPitch(int pitch) {
        if(pitch > getCameraPitch()) {
            context.getKeyboard().holdUntil(KeyEvent.VK_UP, () -> getCameraPitch() >= pitch);
        } else {
            context.getKeyboard().holdUntil(KeyEvent.VK_DOWN, () -> getCameraPitch() <= pitch);
        }
    }

    public static int yawDistance(int yaw1, int yaw2) {
        int diff1; int diff2;
        if (yaw1 > yaw2) {
            diff1 = yaw1 - yaw2;
            diff2 = 2048 + yaw2 - yaw1;
        } else {
            diff1 = yaw2 - yaw1;
            diff2 = 2048 + yaw1 - yaw2;

        }
        return diff1 < diff2 ? diff1 : diff2;
    }

    public int getCameraPitch() {
        return context.getClient().getCameraPitch();
    }

    public int getCameraX() {
        return context.getClient().getCameraX();
    }

    public int getCameraY() {
        return context.getClient().getCameraY();
    }

    public int getCameraZ() {
        return context.getClient().getCameraZ();
    }

    public int getCameraYaw() {
        return context.getClient().getCameraYaw();
    }

    public static void main(String[] args) {
        System.out.println(yawDistance(1024, 512));
    }
}
