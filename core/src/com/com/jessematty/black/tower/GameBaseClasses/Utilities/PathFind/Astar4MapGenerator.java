package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind;

import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
/** @author Nathan Sweet */
public class Astar4MapGenerator {
    private final int width, height;
    private final BinaryHeap<PathNode> open;
    private final PathNode[] nodes;
    int runID;
    private final IntArray path = new IntArray();
    private int targetX, targetY;
  final private GameMap map;
    ArrayList<BooleanStat> tileFlags= new ArrayList<BooleanStat>();
    boolean occupied;
    int pathWidth;
    public Astar4MapGenerator(int width, int height , GameMap map ) {
        this.width = width;
        this.height = height;
        open = new BinaryHeap(width * 4, false);
        nodes = new PathNode[width * height];
        this.map=map;
    }
    /** Returns x,y pairs that are the path from the target to the start. */
    public IntArray getPath(int startX, int startY, int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        path.clear();
        open.clear();
        runID++;
        if (runID < 0) runID = 1;
        int index = startY * width + startX;
       PathNode root = nodes[index];
        if (root == null) {
            root = new PathNode(0);
            root.x = startX;
            root.y = startY;
            nodes[index] = root;
        }
        root.parent = null;
        root.pathCost = 0;
        open.add(root, 0);
        int lastColumn = width - 1, lastRow = height - 1;
        int i = 0;
        while (open.size > 0) {
            PathNode node = open.pop();
            if (node.x == targetX && node.y == targetY) {
                while (node != root) {
                    path.add(node.x);
                    path.add(node.y);
                    node = node.parent;
                }
                break;
            }
            node.closedID = runID;
            int x = node.x;
            int y = node.y;
            if (x < lastColumn) {
                addNode(node, x + 1, y, 10);
            }
            if (x > 0) {
                addNode(node, x - 1, y, 10);
            }
            if (y < lastRow) addNode(node, x, y + 1, 10);
            if (y > 0) addNode(node, x, y - 1, 10);
            i++;
        }
        return path;
    }
    private void addNode(PathNode parent, int x, int y, int cost) {
        if (!isValid(x, y)) return;
        int pathCost = parent.pathCost + cost;
        float score = pathCost + Math.abs(x - targetX) + Math.abs(y - targetY);
        int index = y * width + x;
        PathNode node = nodes[index];
        if (node != null && node.runID == runID) { // Node already encountered for this run.
            if (node.closedID != runID && pathCost < node.pathCost) { // Node isn't closed and new cost is lower.
                // Update the existing node.
                open.setValue(node, score);
                node.parent = parent;
                node.pathCost = pathCost;
            }
        } else {
            // Use node from the cache or createFromJson WoodWand new one.
            if (node == null) {
                node = new PathNode(0);
                node.x = x;
                node.y = y;
                nodes[index] = node;
            }
            open.add(node, score);
            node.runID = runID;
            node.parent = parent;
            node.pathCost = pathCost;
        }
    }
    protected boolean isValid (int x, int y) {
        LandSquareTile tile=map.getTile(x,y);
        return  flagCheck(tile);
    }
    boolean flagCheck(LandSquareTile location){
        return true;
    }
    public int getWidth () {
        return width;
    }
    public int getHeight () {
        return height;
    }
    static private class PathNode extends BinaryHeap.Node {
        int runID, closedID, x, y, pathCost;
        PathNode parent;
        public PathNode (float value) {
            super(value);
        }
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    public int getPathWidth() {
        return pathWidth;
    }
    public void setPathWidth(int pathWidth) {
        this.pathWidth = pathWidth;
    }
    public ArrayList<BooleanStat> getTileFlags() {
        return tileFlags;
    }
    public void setTileFlags(ArrayList<BooleanStat> tileFlags) {
        this.tileFlags = tileFlags;
    }
}
