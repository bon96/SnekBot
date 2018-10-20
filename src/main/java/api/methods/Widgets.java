package api.methods;

import api.MethodContext;
import api.wrappers.RSWidget;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 19.49
 */

public class Widgets {
    MethodContext context;

    public Widgets(MethodContext context) {
        this.context = context;
    }


    public RSWidget get(int parent, int child) {
        for (RSWidget widget : context.getClient().getWidgets()) {
            int[] ids = widget.getWidgetIDs();
            if (ids[0] == parent && ids[1] == child) {
                return widget;
            }
        }
        return null;
    }

}

