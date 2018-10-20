package api.methods;

import api.MethodContext;
import api.input.ClickMode;
import api.utilities.Containerutils;
import api.utilities.Random;
import api.utilities.screen.Projection;
import api.utilities.screen.ScreenLocation;
import api.wrappers.RSActor;

import java.awt.*;
import java.awt.event.MouseEvent;

import static api.MethodContext.sleepUntil;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 14.14
 */

public class Actor {
    MethodContext context;
    RSActor wrapper;

    public Actor(MethodContext context, RSActor wrapper) {
        this.context = context;
        this.wrapper = wrapper;
    }

    public Actor(MethodContext context, Object object) {
        this.context = context;
        this.wrapper = new RSActor(context.getInstance(), object);
    }

    public boolean interact(String action) {
        if (!this.isOnScreen()) {
            context.getCamera().rotateTo(this);
        }
        if (this.isOnScreen()) {
            if (context.getMouse().move(getSuitablePoint())) {
                if (sleepUntil(() -> context.getClient().getMenu().getFirstMenuRow()[0].contains(getName()) && context.getClient().getMenu().getFirstMenuRow()[1].equals(action), Random.nextInt(300, 500))) {
                    context.getMouse().click(ClickMode.LEFT_BUTTON);
                    return true;
                } else {
                    if (context.getClient().getMenu().optionsContain(getName()) && context.getClient().getMenu().actionsContain(action)) {
                        context.getMouse().click(ClickMode.RIGHT_BUTTON);
                        if (sleepUntil(() -> context.getClient().isMenuOpen(), 4000)) {
                            if (context.getMouse().move(context.getClient().getMenu().getMenuActionRectangle(action).getGaussianPoint().toPoint())) {
                                context.getMouse().click(ClickMode.LEFT_BUTTON);
                                if (sleepUntil(() -> !context.getClient().isMenuOpen(), 4000)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Point getSuitablePoint() {
        return this.getModel().getScreenRectangle().getGaussianPoint().toPoint();
    }

    public Tile getTile() {
        return new Tile((this.wrapper.getFineX() >> 7) + context.getClient().getBaseX(),
                (this.wrapper.getFineY() >> 7) + context.getClient().getBaseY());
    }

    public ScreenLocation getScreenLocation() {
        return Projection.fineToScreen(context, getFineX(), getfineY(), getTile().getZ());
    }

    public int getOrientation() {
        return wrapper.getOrientation();
    }

    public boolean isOnScreen() {
        return Projection.GAME_SCREEN.contains(Projection.fineToScreen(context, getFineX(), getfineY(), getTile().getZ()));
    }


    //To be overridden
    public Model getModel() {
        return null;
    }

    //To be overridden
    public String getName() {
        return null;
    }

    public int getX() {
        return getTile().getX();
    }

    public int getY() {
        return getTile().getY();
    }

    public int getFineX() {
        return wrapper.getFineX();
    }

    public int getfineY() {
        return wrapper.getFineY();
    }

    public int getAnimation() {
        return wrapper.getAnimation();
    }

    public int getAnimationDelay() {
        return wrapper.getAnimationDelay();
    }

    public int getCombatTime() {
        return wrapper.getCombatTime();
    }

    public double distance(Actor actor) {
        return this.getTile().distance(actor.getTile());
    }

    public double distance(Tile tile) {
        return this.getTile().distance(tile);
    }

    public RSActor getWrapper() {
        return wrapper;
    }


    //public LocalTile getLocalTile()

}
