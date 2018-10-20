package api.wrappers;

import client.BotInstance;

import static client.help.StringModifier.cutStringAfter;
import static client.help.StringModifier.reverseString;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 11.49
 */

public class RSWidget extends RSWrapper {


    public RSWidget(BotInstance instance, Object object) {
        super(instance, object);
    }

    public int[] getWidgetIDs() {
        int parent;
        int child;

        String string = reverseString(Integer.toBinaryString(getRawID()));
        String[] parts = cutStringAfter(string, 8);

        if (parts.length > 3) {
            parent = Integer.parseInt(reverseString(parts[2] + parts[3]), 2);
        } else {
            parent = Integer.parseInt(reverseString(parts[2]), 2);
        }
        child = Integer.parseInt(reverseString(parts[0] + parts[1]), 2);

        return new int[]{parent, child};
    }

    public boolean isVisible() {
        return !((boolean) getHidden());
    }




    public String getName() {
        return (String) instance.getHookLoader().getFieldValue("Widget.name", object);
    }

    public String getText() {
        return (String) instance.getHookLoader().getFieldValue("Widget.text", object);
    }

    public int getType() {
        return (int) instance.getHookLoader().getFieldValue("Widget.type", object);
    }

    public int getWidth() {
        return (int) instance.getHookLoader().getFieldValue("Widget.width", object);
    }

    public int getHeight() {
        return (int) instance.getHookLoader().getFieldValue("Widget.height", object);
    }

    public int getX() {
        return (int) instance.getHookLoader().getFieldValue("Widget.x", object);
    }

    public int getY() {
        return (int) instance.getHookLoader().getFieldValue("Widget.y", object);
    }

    public int getParentID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.parentId", object);
    }

    public int getItemID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.itemId", object);
    }

    public int getItemStackSize() {
        return (int) instance.getHookLoader().getFieldValue("Widget.itemStackSize", object);
    }

    public int getScrollMax() {
        return (int) instance.getHookLoader().getFieldValue("Widget.scrollMax", object);
    }

    public int getScrollX() {
        return (int) instance.getHookLoader().getFieldValue("Widget.scrollX", object);
    }

    public int getScrollY() {
        return (int) instance.getHookLoader().getFieldValue("Widget.scrollY", object);
    }

    public int getActionType() {
        return (int) instance.getHookLoader().getFieldValue("Widget.actionType", object);
    }

    public int getBorderThickness() {
        return (int) instance.getHookLoader().getFieldValue("Widget.borderThickness", object);
    }

    public int getBoundsIndex() {
        return (int) instance.getHookLoader().getFieldValue("Widget.boundsIndex", object);
    }

    public int getChildTextureID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.childTextureId", object);
    }

    public int getComponentIndex() {
        return (int) instance.getHookLoader().getFieldValue("Widget.componentIndex", object);
    }

    public int getDisabledMediaID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.disabledMediaId", object);
    }

    public int getDisabledMediaType() {
        return (int) instance.getHookLoader().getFieldValue("Widget.disabledMediaType", object);
    }

    public int getEnabledMediaId() {
        return (int) instance.getHookLoader().getFieldValue("Widget.enabledMediaId", object);
    }

    public int getEnabledMediaType() {
        return (int) instance.getHookLoader().getFieldValue("Widget.enabledMediaType", object);
    }

    public int getLoopCycle() {
        return (int) instance.getHookLoader().getFieldValue("Widget.loopCycle", object);
    }

    public int getShadowColor() {
        return (int) instance.getHookLoader().getFieldValue("Widget.shadowColor", object);
    }

    public int getSpriteID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.spriteId", object);
    }

    public int getTextColor() {
        return (int) instance.getHookLoader().getFieldValue("Widget.textColor", object);
    }

    public int getTextureID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.textureId", object);
    }

    //Atleast inventory item IDs are +1 of the real values.
    public int[] getRawSlotIDs() {
        return (int[]) instance.getHookLoader().getFieldValue("Widget.slotIds", object);
    }


    public int[] getSlotStackSizes() {
        return (int[]) instance.getHookLoader().getFieldValue("Widget.slotStackSizes", object);
    }


    public int getRawID() {
        return (int) instance.getHookLoader().getFieldValue("Widget.id", object);
    }

    public Object getActions() {
        return instance.getHookLoader().getFieldValue("Widget.actions", object);
    }

    public Object getChildren() {
        return instance.getHookLoader().getFieldValue("Widget.children", object);
    }

    public Object getDynamicValue() {
        return instance.getHookLoader().getFieldValue("Widget.dynamicValue", object);
    }

    public Object getHidden() {
        return instance.getHookLoader().getFieldValue("Widget.hidden", object);
    }

    public Object getParent() {
        return instance.getHookLoader().getFieldValue("Widget.parent", object);
    }

    public Object getSelectedAction() {
        return instance.getHookLoader().getFieldValue("Widget.selectedAction", object);
    }

    public Object getSpell() {
        return instance.getHookLoader().getFieldValue("Widget.spell", object);
    }

    public Object getTooltip() {
        return instance.getHookLoader().getFieldValue("Widget.tooltip", object);
    }
}
