package api.utilities;

import api.utilities.screen.ScreenLocation;
import api.utilities.screen.geometry.ScreenRectangle;

/**
 * BonBom
 * Date: 8.1.2018
 * Time: 14.11
 */

public class Containerutils {

    public static ScreenRectangle getInventorySlotRectangle(int slot) {
        int n2 = slot / 4;
        int n3 = slot % 4;
        return new ScreenRectangle(563 + n3 * 42, 213 + n2 * 36, 31, 31);
    }


    public static ScreenRectangle getMenuActionRectangle(int menuX, int menuY, int actionNumber) {
        menuX += 1;
        menuY += 19;
       return new ScreenRectangle(menuX, menuY + actionNumber * 15, 80, 15);
    }

    public static ScreenLocation getSuitablePoint(ScreenRectangle rectangle) {
        return rectangle.getGaussianPoint();
    }
}
