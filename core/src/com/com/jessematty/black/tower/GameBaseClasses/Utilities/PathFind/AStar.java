package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation.IsValid;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation.IsValidForEntity;
import com.jessematty.black.tower.Maps.GameMap;
/** @author Nathan Sweet */
public class AStar {
    /**
     * the width and height of the map
     */
    private final int width, height;
    /**
     * heaps of nodes for path finding
     */
    private final BinaryHeap<PathNode> open;
    /**
     * the array of nodes
     */
    private final PathNode[] nodes;
    int runID;
    /**
     * the array of ending points
     */
    private final IntArray path = new IntArray();
    /**
     * the end points x and y coordinates
     */
    private int targetX, targetY;
    /**
     * the map to path find on
     */
    boolean pathFindDiagonals=true;

    /**
     * functional interface  for calling the isValid(x, y) method to determine if a give tile is valid movement for path finding
     */
    private IsValid isValid;
    public AStar(int width, int height , GameMap map , Entity entity) {
        this.width = width;
        this.height = height;
        open = new BinaryHeap(width * 4, false);
        nodes = new PathNode[width * height];

        isValid= new IsValidForEntity(map, entity);
    }
    public AStar(int width, int height, GameMap map) {
        this.width = width;
        this.height = height;
        open = new BinaryHeap(width * 4, false);
        nodes = new PathNode[width * height];
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
           if(pathFindDiagonals){
               addNodeEightDirections(x, y, lastRow, lastColumn, node);
           }

           else{
               addNodeFourDirections(x, y, lastRow, lastColumn, node);
           }
            i++;
        }
        return path;
    }

    private void  addNodeEightDirections(int x , int y , int lastRow, int lastColumn, PathNode node){
        if (x < lastColumn) {
            addNode(node, x + 1, y, 10);
            if (y < lastRow)
                addNode(node, x + 1, y + 1, 14); // Diagonals cost more, roughly equivalent to sqrt(2).
            if (y > 0) addNode(node, x + 1, y - 1, 14);
        }
        if (x > 0) {
            addNode(node, x - 1, y, 10);
            if (y < lastRow) addNode(node, x - 1, y + 1, 14);
            if (y > 0) addNode(node, x - 1, y - 1, 14);
        }
        if (y < lastRow) addNode(node, x, y + 1, 10);
        if (y > 0) addNode(node, x, y - 1, 10);

    }

    private void  addNodeFourDirections(int x , int y , int lastRow, int lastColumn, PathNode node){
        if (x < lastColumn) {
            addNode(node, x + 1, y, 10);
        }
        if (x > 0) {
            addNode(node, x - 1, y, 10);
        }
        if (y < lastRow) addNode(node, x, y + 1, 10);
        if (y > 0) addNode(node, x, y - 1, 10);
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
            // Use node from the cache or create a new one.
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
     return isValid.isValid(x, y);
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

    public IsValid getIsValid() {
        return isValid;
    }

    public void setIsValid(IsValid isValid) {
        this.isValid = isValid;
    }

    public boolean isPathFindDiagonals() {
        return pathFindDiagonals;
    }

    public void setPathFindDiagonals(boolean pathFindDiagonals) {
        this.pathFindDiagonals = pathFindDiagonals;
    }


}
