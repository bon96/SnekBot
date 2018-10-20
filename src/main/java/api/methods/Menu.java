package api.methods;

import api.wrappers.RSClient;
import api.utilities.Containerutils;
import api.utilities.screen.geometry.ScreenRectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 21.1.2018
 * Time: 14.20
 */

public class Menu {
    RSClient client;

    public Menu(RSClient client) {
        this.client = client;
    }


    public String[] getFirstMenuRow() {
        return new String[]{getMenuOptions().get(0), getMenuActions().get(0)};
    }

    public List<String> getMenuActions() {
        String[] actions = client.getMenuActionsReference();
        List<String> list = new ArrayList<>();
        int count = client.getMenuCount();
        for (int i = count -1; i >= 0; i--) {
            list.add(actions[i]);
        }
        return list;
    }

    public List<String> getMenuOptions() {
        String[] options = client.getMenuOptionsReference();
        List<String> list = new ArrayList<>();
        int count = client.getMenuCount();
        for (int i = count -1; i >= 0; i--) {
            list.add(options[i]);
        }
        return list;
    }

    public ScreenRectangle getMenuActionRectangle(String action) {
        return Containerutils.getMenuActionRectangle(client.getMenuX(), client.getMenuY(), getActionNumber(action));
    }

    public int getOptionNumber(String option) {
        int number = 0;
        for (String string : getMenuOptions()) {
            if (string.equals(option)) {
                return number;
            }
            number++;
        }
        return -1;
    }

    public int getActionNumber(String action) {
        int number = 0;
        for (String string : getMenuActions()) {
            if (string.equals(action)) {
                return number;
            }
            number++;
        }
        return -1;
    }

    public boolean optionsContain(String option) {
        for (String string : getMenuOptions()) {
            if (string.contains(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean actionsContain(String action) {
        for (String string : getMenuActions()) {
            if (string.contains(action)) {
                return true;
            }
        }
        return false;
    }

}
