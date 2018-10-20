package api.wrappers;

import api.wrappers.RSRenderable;
import client.BotInstance;

/**
 * BonBom
 * Date: 7.1.2018
 * Time: 12.05
 */

public class RSModel extends RSRenderable {


    public RSModel(BotInstance instance, Object object) {
        super(instance, object);
    }

    public int getVerticesCount() {
        return Math.min(this.getXVertices().length, Math.min(this.getYVertices().length, this.getZVertices().length));
    }
    public int getIndicesLength() {
        return (int) instance.getHookLoader().getFieldValue("Model.indicesLength", object);
    }

    public int[] getXTriangles() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.indicesX", object);
    }

    public int[] getYTriangles() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.indicesY", object);
    }

    public int[] getZTriangles() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.indicesZ", object);
    }

    public int[] getOnCursorUIDs() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.onCursorUIDs", object);
    }

    public int getuidCount() {
        return (int) instance.getHookLoader().getFieldValue("Model.uidCount", object);
    }

    public int[][] getVectorSkin() {
        return (int[][]) instance.getHookLoader().getFieldValue("Model.vectorSkin", object);
    }

    public int getVerticesLength() {
        return (int) instance.getHookLoader().getFieldValue("Model.verticesLength", object);
    }

    public int[] getXVertices() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.verticesX", object);
    }

    public int[] getYVertices() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.verticesY", object);
    }

    public int[] getZVertices() {
        return (int[]) instance.getHookLoader().getFieldValue("Model.verticesZ", object);
    }

}
