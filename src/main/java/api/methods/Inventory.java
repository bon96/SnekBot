package api.methods;

import api.MethodContext;
import api.input.ClickMode;
import api.utilities.Containerutils;
import api.utilities.screen.geometry.ScreenRectangle;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static api.MethodContext.sleepUntil;

/**
 * BonBom
 * Date: 7.1.2018
 * Time: 17.59
 */

public class Inventory {
    MethodContext context;

    public Inventory(MethodContext context) {
        this.context = context;
    }

    public boolean dropAll() {
        return dropAllInRange(0, 28);
    }


    public boolean dropAllInRange(int i1, int i2) {
        for (int i = i1; i < i2; i++) {
            if (context.getMouse().click(context.getInventory().getSlotDestination(i).getGaussianPoint(), ClickMode.RIGHT_BUTTON)
                    && sleepUntil(() -> context.getClient().isMenuOpen(), 8000)) {
                if (context.getClient().getMenu().actionsContain("Drop")) {
                    context.getMouse().click(context.getClient().getMenu().getMenuActionRectangle("Drop").getGaussianPoint());
                } else {
                    context.getMouse().click(context.getClient().getMenu().getMenuActionRectangle("Cancel").getGaussianPoint());
                }
            }
        }
        return true;
    }

    public ScreenRectangle getSlotDestination(int slot) {
        return Containerutils.getInventorySlotRectangle(slot);
    }

    public List<Integer> getIDs() {
        List<Integer> list = new ArrayList<>();
        int[] ids =  context.getWidgets().get(149, 0).getRawSlotIDs();
        for (int integer : ids) {
            if (integer > 0) {
                list.add(integer - 1);
            } else {
                list.add(integer);
            }
        }
        return list;
    }

    public boolean isFull() {
        for (int integer : getStackSizes()) {
            if (integer == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(int id) {
        for (int integer : getIDs()) {
            if (integer == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (int integer : getStackSizes()) {
            if (integer > 0) {
                return false;
            }
        }
        return true;
    }

    public int getEmptySlots() {
        int emptySlots = 0;
        for (int integer : getStackSizes()) {
            if (integer == 0) {
                emptySlots++;
            }
        }
        return emptySlots;
    }

    public boolean isItemSelected() {
        return context.getClient().getSelectionState() > 0;
    }

    public boolean deselectItem() {
        if (isItemSelected()) {
            context.getMouse().click(MouseEvent.BUTTON3);
            if (sleepUntil(() -> context.getClient().isMenuOpen(), 8000)) {
                context.getMouse().move(Containerutils.getMenuActionRectangle(context.getClient().getMenuX(), context.getClient().getMenuY(),
                        context.getClient().getMenu().getActionNumber("Cancel")).getGaussianPoint().toPoint());
                context.getMouse().click(MouseEvent.BUTTON1);
                if (sleepUntil(() -> !isItemSelected(), 8000)) {
                    return true;
                }
            }
        }
        return false;
    }


    public int[] getStackSizes() {
        return context.getWidgets().get(149, 0).getSlotStackSizes();
    }
}
