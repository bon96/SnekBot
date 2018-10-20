package api.methods;

import api.MethodContext;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 15.57
 */

public class Players {
    MethodContext context;

    public Players(MethodContext context) {
        this.context = context;
    }

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        Object[] players = (Object[]) context.getClient().getPlayersReference();
        for (Object object : players) {
            if (object != null) {
                list.add(new Player(context, object));
            }
        }
        return list;
    }
}
