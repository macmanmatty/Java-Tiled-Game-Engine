package com.jessematty.black.tower.AI.Movement;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Entity.Functions.CharacterMoveFunctions;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.AStar;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MoveToLocation extends ZRPGAIAction {
   private LandSquareTile targetTile;
   private Array<LandSquareTile> placesToMove;
  private GameMap map;
   private  int xSize;
    private int ySize;
    private PositionComponent position;
   private MovableComponent movableComponent;
    public MoveToLocation(ZRPGCharacter zrpgCharacter, GameMap map) {
        super(zrpgCharacter);
        this.xSize=map.getXTiles();
        this.ySize=map.getYTiles();
        this.map=map;
        position=zrpgCharacter.getPositionComponent();
        movableComponent=zrpgCharacter.getMovableComponent();
    }

    public MoveToLocation(ZRPGCharacter zrpgCharacter, GameMap map,  LandSquareTile targetTile) {
        this(zrpgCharacter, map);
        this.targetTile = targetTile;
    }

    /**
     * overridden act method  that uses AStar  path finding to calculate  a path for the Character to move to a given location
     * runs A Star each call because the path required may have changed due  to other Entities Characters / Traps Etc. blocking it
     * will call move. stop and return -1 when the path is finished or when no path is available to be taken.
     * @param deltaTime libGDX render method  delta time
     * @return
     */
    @Override
    public int  act(float deltaTime) {
        // no places to move try to find them
        if (placesToMove == null) {
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() , targetTile.getScreenLocationX(), targetTile.getScreenLocationY(),0,0, xSize, ySize);
        }

        // no place to move to you are either done or can't get to the tile
        if (placesToMove.size == 0) {
            // stop movable is done moving
            CharacterMoveFunctions.stop(zrpgCharacter);
            return -1;
        }
        // get next tile  to move to
        LandSquareTile nextTile=placesToMove.get(0);
        // if you cant enter the tile or the tile is occupied recalculate the the path
          calculateAndSetMoveDirection(position.getTileLocationX(), position.getTileLocationY(), nextTile.getLocationX(), nextTile.getLocationY());
          LandSquareTile tile=map.getTileFromWorldUnitCoordinates(position.getLocationX(), position.getLocationY());
            if(InList.isInList(nextTile, position.getTiles())) {
                placesToMove.removeValue(nextTile, false);
            }

        if(placesToMove.size==0){
            CharacterMoveFunctions.stop(zrpgCharacter);
            return -1;
        }
        return placesToMove.size;
        }

    /**
     *method for finding the path from tile  a to tile b  calculates the path with  the path finding method using the A* algorithm
     * @param map the game map
     * @param worldLocationX the x  start point   in  wold units
     * @param worldLocationY the y world   point   in  wold units
     * @param worldLocationXEnd the x world  end point    in  wold units
     * @param worldLocationYEnd the y world  end point    in  wold units
     * @param xStart
     * @param yStart
     * @param xMax
     * @param yMax
     * @return
     */
    public Array<LandSquareTile> pathFind(GameMap map,  float worldLocationX, float worldLocationY, float worldLocationXEnd, float worldLocationYEnd, int xStart, int yStart, int xMax, int yMax) {
        LandSquareTile tileFrom=map.getTileFromWorldUnitCoordinates(worldLocationX, worldLocationY);
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        LandSquareTile tileTo=map.getTileFromWorldUnitCoordinates(worldLocationXEnd, worldLocationYEnd);
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        int width=xMax-xStart;
        int height=yMax-yStart;
        Array<LandSquareTile> tiles= new Array<LandSquareTile>();

            AStar star = new AStar(width, height, map, zrpgCharacter.getPlayerEntity());
            star.setPathFindDiagonals(movableComponent.isEightDirections());
            IntArray paths=star.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getTile(paths.get(count-1), paths.get(count)));
            }

        return tiles;
    }

    /**
     *Takes two tile and determines the direction one would have to travel to get from
     * tile one to tile two then  set the Characters movable component to that direction;
     * @return
     * @param tileX
     * @param tileY
     * @param tile2X
     * @param tile2Y
     */
    public void calculateAndSetMoveDirection(int tileX, int tileY, int tile2X, int tile2Y) {
       int locationXDifference = tileX - tile2X;
        int locationYDifference = tileY - tile2Y;
        if (locationYDifference > 0 && locationXDifference == 0) {
            movableComponent.moveUp();
        } else if (locationYDifference < 0 && locationXDifference == 0) {
            movableComponent.moveDown();
        } else if (locationXDifference < 0 && locationYDifference == 0) {
            movableComponent.moveRight();
        } else if (locationXDifference > 0 && locationYDifference == 0) {
            movableComponent.moveLeft();
        } else if (locationYDifference < 0 && locationXDifference > 0) {
            movableComponent.moveLeftDown();
        } else if (locationYDifference > 0 && locationXDifference > 0) {
            movableComponent.moveLeftUp();
        } else if (locationYDifference < 0 && locationXDifference < 0) {
           movableComponent.moveRightDown();
        } else if (locationYDifference > 0 && locationXDifference < 0) {
            movableComponent.moveRightUp();
        } ;
        return;
    }
}
