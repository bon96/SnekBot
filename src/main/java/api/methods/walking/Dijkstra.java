package api.methods.walking;

import api.methods.Tile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * BonBom
 * Date: 26.1.2018
 * Time: 18.49
 */

public class Dijkstra {
    public PathNode[] nodes;

    private PathNode start;
    private PathNode end;

    private List<PathNode> unvisited = new ArrayList<>();
    private Map<PathNode, Double>  costs = new HashMap<>();

    public Dijkstra(PathNode[] nodes) {
        this.nodes = nodes;
    }


    public List<Tile> calculate() {
        PathNode current = getStart();

        unvisited.addAll(Arrays.asList(nodes));
        unvisited.remove(current);

        for (PathNode node : nodes) {
            costs.put(node, Double.MAX_VALUE);
            node.shortestPath.add(getStart());
        }
        costs.put(current, 0D);

        while(true) {
            List<PathNode> neighbours = getNeighbours(current);

            for (PathNode node : neighbours) {
                if (costs.get(current) + node.getCost() < costs.get(node)) {
                    costs.put(node, costs.get(current) + node.getCost());
                    node.shortestPath = new ArrayList<>(current.shortestPath);
                    node.shortestPath.add(node);
                }
            }
            if (current.equals(getEnd())) {
                break;
            }
            current = getLowestCost();
            unvisited.remove(current);
        }
        current.shortestPath.add(getEnd());
        return toTile(current.shortestPath);
    }

    private PathNode getLowestCost() {
        PathNode lowestCost = null;

        for (PathNode node : unvisited) {
            if (lowestCost == null || (costs.get(node)) < costs.get(lowestCost)) {
                lowestCost = node;
            }
        }
        return lowestCost;
    }

    private List<PathNode> getNeighbours(PathNode baseNode) {
        List<PathNode> neighbours = new ArrayList<>();

        for (PathNode node : unvisited) {
            if (distance(baseNode, node) < 2) {
                neighbours.add(node);
            }
        }
        return neighbours;
    }

    public double distance (PathNode node1, PathNode node2) {
        final Point point1 = new Point(node1.getX(), node1.getY());
        final Point point2 = new Point(node2.getX(), node2.getY());

        return point1.distance(point2);
    }

    private PathNode getNode(int x, int y) {
        for (PathNode node : nodes) {
            if (node.getX() == x && node.getY() == y) {
                return node;
            }
        }
        return null;
    }

    public static List<Tile> toTile(List<PathNode> nodes) {
        List<Tile> list = new ArrayList<>();

        for (PathNode node : nodes) {
            list.add(node.toTile());
        }
        return list;
    }

    public PathNode getStart() {
        return start;
    }

    public void setStart(PathNode start) {
        this.start = start;
    }

    public PathNode getEnd() {
        return end;
    }

    public void setEnd(PathNode end) {
        this.end = end;
    }

    public static void main(String[] args) {
        List<PathNode> list = new ArrayList<>();

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                list.add(new PathNode(x, y));
            }
        }

        Dijkstra dijkstra = new Dijkstra(list.toArray(new PathNode[list.size()]));
        dijkstra.setStart(dijkstra.getNode(50, 50));
        dijkstra.setEnd(dijkstra.getNode(0, 0));

        for (Tile tile : dijkstra.calculate()) {
            System.out.println(tile.getX() + " " + tile.getY());
        }
    }
}
