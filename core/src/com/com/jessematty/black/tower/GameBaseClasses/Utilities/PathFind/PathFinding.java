package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind;

import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation.IsValid;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * class that hold basic path finding functions to ne used with a GameMap LandSquareTiles / and  / Or Entities
 *
 */
public class PathFinding {

    /**
     * path finding class
     */
   private AStar astar;
    /**
     * the Map to path find on
     */
   private GameMap map;

    public PathFinding(GameMap map) {
        this.map = map;
        astar = new AStar(map.getXTiles(), map.getYTiles(), map);
    }
    public PathFinding() {
    }

    public AStar getAstar() {
        return astar;
    }
    public GameMap getMap() {
        return map;
    }

    /**
     * sets the GameMap and initlizes the path finding objects;
     * @param map The GameMap to path find on
     */
    public void setMap(GameMap map) {
        this.map = map;
        astar = new AStar(map.getXTiles(), map.getYTiles(), map);
    }

    /**
     * checks to see if a  clear path exists between two map  LandSquareTiles
     * @param tile starting tile
     * @param tile2 the end tile
     * @param pathFindDiagonals  whether or not to path find using diagonals
     * @param isValid  function to check whether or not a given tile is valid movement
     * @param  map the GameMap to path find on
     * @return whether or not the path exists
     */
    boolean pathExists(GameMap map, LandSquareTile tile , LandSquareTile tile2, boolean pathFindDiagonals, IsValid isValid){
        return  pathExists(tile.getLocationX(), tile.getLocationY(), tile2.getLocationX(), tile2.getLocationY(), pathFindDiagonals, isValid);
    }
    /**
     * checks to see if a  clear path exists between two points
     * @param startX the x position of the starting point
     * @param startY the y position of the starting point
     * @param endX the x position of the ending  point
     * @param endY the y position of the ending point
     * @param pathFindDiagonals  whether or not to path find using diagonals
     * @param isValid  function to check whether for not a given tile is valid movement
     * @return whether or not the path exists
     */
    boolean pathExists(int startX, int startY, int endX, int endY, boolean pathFindDiagonals, IsValid isValid){
        astar.setPathFindDiagonals(pathFindDiagonals);
        astar.setIsValid(isValid);
        IntArray tiles= astar.getPath(startX, startY, endX, endY);
        if(tiles.size>0){
            return  true;
        }
        else{
            return  false;
        }

    }
}
