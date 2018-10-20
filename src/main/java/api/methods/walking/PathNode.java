package api.methods.walking;

import api.methods.Tile;

import java.util.ArrayList;
import java.util.List;


/**
 * BonBom
 * Date: 26.1.2018
 * Time: 18.51
 */

public class PathNode {
    private int x;
    private int y;
    private double cost;

    public List<PathNode> shortestPath = new ArrayList<>();

    public PathNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.cost = 1;
    }

    public PathNode(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public Tile toTile() {
        return new Tile(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public double getPathCost() {
        return getPathCost(shortestPath.toArray(new PathNode[shortestPath.size()]));
    }


    public static double getPathCost(PathNode[] nodes) {
        double cost = 0;

        for (PathNode node : nodes) {
            cost += node.getCost();
        }

        return cost;
    }
}
