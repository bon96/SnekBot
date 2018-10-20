package api.methods;

import api.MethodContext;
import api.utilities.Containerutils;
import api.utilities.ModelFromCache;
import api.utilities.Random;
import api.wrappers.RSNpcComposite;
import api.wrappers.RSNPC;

import java.awt.*;
import java.awt.event.MouseEvent;

import static api.MethodContext.sleepUntil;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 15.50
 */

public class NPC extends Actor {
    MethodContext context;
    RSNPC wrapper;

    public NPC(MethodContext context, RSNPC wrapper) {
        super(context, wrapper);
        this.context = context;
        this.wrapper = wrapper;
    }

    public NPC(MethodContext context, Object object) {
        super(context, new RSNPC(context.getInstance(), object));
        this.context = context;
        wrapper = new RSNPC(context.getInstance(), object);
    }

    @Override
    public Model getModel() {
        Model model = new Model(context, ModelFromCache.getNpcModel(context, this));
        model.rotateTo(getOrientation());
        model.place(getFineX(), getfineY());
        return model;
    }

    @Override
    public String getName() {
        return getComposite().getName();
    }

    public RSNpcComposite getComposite() {
        return wrapper.getComposite();
    }


    @Override
    public RSNPC getWrapper() {
        return wrapper;
    }
}
