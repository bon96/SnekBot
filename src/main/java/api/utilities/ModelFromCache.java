package api.utilities;

import api.MethodContext;
import api.methods.NPC;
import api.methods.Player;
import api.wrappers.RSGameObjectComposite;
import api.wrappers.RSNode;
import api.wrappers.RSModel;
import api.wrappers.RSCache;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 11.1.2018
 * Time: 15.09
 */

public class ModelFromCache {

    private ModelFromCache(){}



    public static RSModel getPlayerModel(MethodContext context, Player player) {
        long playerID = player.getPlayerComposite().getStaticModelID();
        RSCache cache = new RSCache(context.getInstance(), context.getClient().getPlayerModelCacheReference());

        for (RSNode node : cache.getHashTable().getBuckets()) {
            RSNode node2 = node.getNext();
            long id = node2.getID();
            if (id == playerID) {
                return new RSModel(context.getInstance(), node2.getObject());
            }
        }
        return null;
    }

    public static RSModel getNpcModel(MethodContext context, NPC npc) {
        long npcID = npc.getComposite().getID();
        RSCache cache = new RSCache(context.getInstance(), context.getClient().getNpcModelCacheReference());

        for (RSNode node : cache.getHashTable().getBuckets()) {
            RSNode node2 = node.getNext();
            long id = node2.getID();
            if (id == npcID) {
                return new RSModel(context.getInstance(), node2.getObject());
            }
        }
        return null;
    }

    public static RSModel[] getGameObjectModels(MethodContext context) {
        List<RSModel> list = new ArrayList<>();
        RSCache cache = new RSCache(context.getInstance(), context.getClient().getObjectModelCacheReference());

        for(RSNode node : cache.getHashTable().getBuckets()) {
            list.add(new RSModel(context.getInstance(), node.getNext().getObject()));
        }
        return list.toArray(new RSModel[list.size()]);
    }

    public static RSGameObjectComposite[] getGameObjectComposites(MethodContext context) {
        List<RSGameObjectComposite> list = new ArrayList<>();
        RSCache cache = new RSCache(context.getInstance(), context.getClient().getObjectCompositeCacheReference());

        for(RSNode node : cache.getHashTable().getBuckets()) {
            list.add(new RSGameObjectComposite(context.getInstance(), node.getNext().getObject()));
        }
        return list.toArray(new RSGameObjectComposite[list.size()]);
    }
}
