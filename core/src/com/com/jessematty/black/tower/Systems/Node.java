package com.jessematty.black.tower.Systems;

import java.util.ArrayList;

public class Node implements Comparable<Node>  {
    Node parentNode;
    ArrayList<Node> nodes = new ArrayList<Node>();
    boolean passable;
    boolean visited;
    int xLocation;
    int yLocation;
   double gValue;
    double hValue;
    double fValue;


    public Node(Node parentNode, int xLocation, int yLocation, double gValue, double hValue) {
        this.parentNode = parentNode;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.gValue = gValue;
        this.hValue = hValue;
        fValue=gValue+hValue;

    }

    public void addNode(Node node){
        nodes.add(node);

}

    public Node getParentNode() {
        return parentNode;
    }

    public boolean isPassable() {
        return passable;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public double getgValue() {
        return gValue;
    }

    public double gethValue() {
        return hValue;
    }

    public double getfValue() {
        return fValue;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setgValue(int gValue) {
        this.gValue = gValue;
    }

    public void sethValue(int hValue) {
        this.hValue = hValue;
    }


    @Override
    public int compareTo(Node node) {
        if(fValue>node.getfValue()){
            return 1;

        }
        else if(fValue<node.getfValue()) {
            return-1;
        }
        return 0;


    }
}
