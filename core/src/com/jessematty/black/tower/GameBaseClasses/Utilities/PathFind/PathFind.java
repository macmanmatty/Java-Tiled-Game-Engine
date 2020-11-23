package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import java.util.ArrayList;
public class PathFind {
    boolean eightDirections;
    com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar8MapGenerator star8;
    com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar4MapGenerator star4;
    GameMap map;
    public PathFind(GameMap map) {
        this.map = map;
        star4 = new com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar4MapGenerator(map.getXSize(), map.getYSize(), map);
        star8 = new com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar8MapGenerator(map.getXSize(), map.getYSize(), map);
    }
    public ArrayList<LandSquareTile> pathFind(LandSquareTile tileFrom, LandSquareTile tileTo, int width, ArrayList<BooleanStat> flags, boolean occupant, boolean eightDirections) { // calculates  the path finding method using the A* algorithm
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        if (eightDirections==false){
            star8.setOccupied(occupant);
            star8.setPathWidth(width);
            IntArray paths=star4.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        else {
            star4.setOccupied(occupant);
            star4.setPathWidth(width);
            IntArray paths=star8.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        return tiles;
    }
    public ArrayList<LandSquareTile> pathFind(LandSquareTile tileFrom, LandSquareTile tileTo, int width,  BooleanStat flag, boolean occupant, boolean eightDirections) { // calculates  the path finding method using the A* algorithm
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        if (eightDirections==false){
            star8.setOccupied(occupant);
            star8.setPathWidth(width);
            IntArray paths=star4.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        else {
            star4.setOccupied(occupant);
            star4.setPathWidth(width);
            IntArray paths=star8.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        return tiles;
    }
    public ArrayList<LandSquareTile> pathFind(LandSquareTile tileFrom, LandSquareTile tileTo, int xStart, int yStart, int xMax, int yMax, int pathWidth,  BooleanStat flag, boolean occupant, boolean eightDirections) { // calculates  the path finding method using the A* algorithm
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        int width=xMax-xStart;
        int height=yMax-yStart;
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        if (eightDirections==false){
            com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar8MapGenerator star8= new Astar8MapGenerator(width, height, map);
            star8.setOccupied(occupant);
            star8.setPathWidth(pathWidth);
            IntArray paths=star4.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        else {
            com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar4MapGenerator star4= new Astar4MapGenerator(width, height, map);
            star4.setOccupied(occupant);
            star4.setPathWidth(pathWidth);
            IntArray paths=star8.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        return tiles;
    }
    static public  double findDistance(LandSquareTile location1,  LandSquareTile location2) { // calculates the distance bewteen to tiles using the x and y coordinates
        int x2=location2.getLocationX();
        int y2=location2.getLocationY();
        int y1=location1.getLocationY();
        int x1=location1.getLocationX();
        return Math.sqrt(((x2-x1)^2+((y2-y1)^2)));
    }
}
