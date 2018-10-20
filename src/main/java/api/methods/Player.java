package api.methods;

import api.MethodContext;
import api.utilities.Containerutils;
import api.utilities.ModelFromCache;
import api.utilities.Random;
import api.wrappers.RSPlayerComposite;
import api.wrappers.RSPlayer;

import java.awt.*;
import java.awt.event.MouseEvent;

import static api.MethodContext.sleepUntil;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 15.17
 */

public class Player extends Actor {
    RSPlayer wrapper;
    MethodContext context;

    public Player(MethodContext context, RSPlayer wrapper) {
        super(context, wrapper);
        this.context = context;
        this.wrapper = wrapper;
    }

    public Player(MethodContext context, Object object) {
        super(context, new RSPlayer(context.getInstance(), object));
        this.context = context;
        this.wrapper = new RSPlayer(context.getInstance(), object);
    }

    public Point getSuitablePoint() {
        return this.getModel().getScreenRectangle().getGaussianPoint().toPoint();
    }

    @Override
    public String getName() {
        return wrapper.getName();
    }

    public int getLevel() {
        return wrapper.getLevel();
    }


    @Override
    public Model getModel() {
        Model model = new Model(context, ModelFromCache.getPlayerModel(context, this));
        model.rotateTo(getOrientation());
        model.place(getFineX(), getfineY());
        return model;
    }

    public RSPlayerComposite getPlayerComposite() {
        return new RSPlayerComposite(context.getInstance(), wrapper.getComposite());
    }


    @Override
    public RSPlayer getWrapper() {
        return wrapper;
    }


}
