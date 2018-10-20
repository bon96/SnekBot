package api.methods;

import api.MethodContext;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 10.1.2018
 * Time: 16.00
 */

public class Npcs {
    MethodContext context;


    public Npcs(MethodContext context) {
        this.context = context;
    }

    public List<NPC> getNpcs() {
        List<NPC> list = new ArrayList<>();
        Object[] npcs = (Object[]) context.getClient().getLocalNPCsReference();
        for (Object object : npcs) {
            if (object != null) {
                list.add(new NPC(context, object));
            }
        }
        return list;
    }
}
