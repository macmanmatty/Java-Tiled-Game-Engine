package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation.IsValid;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * class that hold basic path finding functions to ne used with a GameMap LandSquareTiles / and  / Or Entities
 * This class will path find on whatever map set in this class.
 *
 */
public class PathFinding  implements WorldSettable {

    /**
     * path finding class
     */
   private AStar astar;
   /**
     * the World to path find on
     */
    private World world;


    public PathFinding(World world) {
        this.world = world;
    }

    public PathFinding() {
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


    /**
     *finds a path for a  ZRPGCharacter based his current location
     * searches the whole map
     * @param map the map to path find on
     * @param startX starting point  x coordinate   in world units
     * @param startY starting point  y  coordinate  in world units
     * @param endX ending  point  x  coordinate  in world units
     * @param endY ending  point  y coordinate in world units
     * @return An Array of LandSquareTiles  the represent  the path to take to get from the start point to the end point
     */
    public Array<LandSquareTile> pathFind(ZRPGCharacter zrpgCharacter, GameMap map, float startX, float startY, float endX, float endY) { // calculates  the path finding method using the A* algorithm

        return pathFind(zrpgCharacter, map, startX, startY, endX, endY, 0, 0, map.getXTiles()-1, map.getYTiles()-1);
    }

    /**
     *finds a path for a  ZRPGCharacter based his current location
     * searches specified area based
     * @param map the map to path find on
     * @param startX starting point  x coordinate   in world units
     * @param startY starting point  y  coordinate  in world units
     * @param endX ending  point  x  coordinate  in world units
     * @param endY ending  point  y coordinate in world units
     * @param searchAreaStartX  x starting path find  area  in world units
     * @param searchAreaStartY y starting path find  area  in world units
     * @param searchAreaWidth x ending  path find  area  in world units
     * @param searchAreaHeight y ending path find  area  in world units
     * @return An Array of LandSquareTiles  the represent  the path to take to get from the start point to the end point
     */
    public Array<LandSquareTile> pathFind(ZRPGCharacter zrpgCharacter, GameMap map, float startX, float startY, float endX, float endY, int searchAreaStartX, int searchAreaStartY, int searchAreaWidth, int searchAreaHeight) { // calculates  the path finding method using the A* algorithm
        LandSquareTile tileFrom=map.getTileFromTileCoordinates(startX, startY);
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        LandSquareTile tileTo=map.getTileFromTileCoordinates(endX, endY);
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        int width=searchAreaWidth-searchAreaStartX;
        int height=searchAreaHeight-searchAreaStartY;
        Array<LandSquareTile> tiles= new Array<LandSquareTile>();
        Entity entity=zrpgCharacter.getPlayerEntity();
        AStar star = new AStar(width, height, map, entity);
        MovableComponent movableComponent= GameComponentMapper.getComponent(MovableComponent.class).get(entity);
        star.setPathFindDiagonals(movableComponent.isEightDirections());
        IntArray paths=star.getPath(fromX, fromY, toX, toY);
        int size=paths.size;
        for (int count=size-1; count>=0; count=count-2){
            tiles.add(map.getTile(paths.get(count-1), paths.get(count)));
        }

        return tiles;
    }

    /**
     *  finds a Path if there is one between to given LandSquareTiles
     * @param tileFrom the start finding the path on
     * @param tileTo the end tile for the path
     * @param pathFindDiagonals  whether or not to path find using diagonals
     * @return An Array of LandSquareTiles  the represent  the path to take to get from the start tile to the end tile
     */
    public Array<LandSquareTile> pathFind( LandSquareTile tileFrom, LandSquareTile tileTo, boolean pathFindDiagonals) { // calculates  the path finding method using the A* algorithm
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        String fromMapId=tileFrom.getMapId();
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        String tileToMapId=tileTo.getMapId();
        Array<LandSquareTile> tiles= new Array<LandSquareTile>();
            //  return empty array because tiles aren't on the same map so you cant path find.
        if(!fromMapId.equals(tileToMapId)){
            return tiles;
        }
        GameMap map=world.getMap(tileToMapId);
           astar.setPathFindDiagonals(pathFindDiagonals);
            IntArray paths=astar.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getTile(paths.get(count-1), paths.get(count)));
            }

        return tiles;
    }

    public AStar getAStar() {
        return astar;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }


}
