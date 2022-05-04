package com.jessematty.black.tower.AI.Movement;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar4;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar8;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.MoveToSingleTile;
import com.jessematty.black.tower.Components.SolidObject;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.Unused.Node;

public class MoveToLocation extends ZRPGAIAction {
   private LandSquareTile moveTile;
   private Array<LandSquareTile> placesToMove;
  private GameMap map;
   private  int xSize;
    private int ySize;
    private PositionComponent position;
   private MovableComponent movableComponent;
    public MoveToLocation(ZRPGCharacter zrpgCharacter) {
        super(zrpgCharacter);
        this.xSize=map.getXTiles();
        this.ySize=map.getYTiles();
        this.map=map;
        position=zrpgCharacter.getPositionComponent();
        movableComponent=zrpgCharacter.getMovableComponent();
    }

    public MoveToLocation(ZRPGCharacter zrpgCharacter, LandSquareTile moveTile) {
        super(zrpgCharacter);
        this.moveTile = moveTile;
    }

    /**
     * overridden act method  that uses AStar  path finding to calculate  a path for the Character to move to a given location
     * runs A Star each call because the path required may have changed due  to other Entities Characters / Traps Etc. blocking it
     * will call move. stop and return -1 when the path is finished or when no path is available to be taken.
     * @param deltaTime libGDX render method  delta time
     * @return
     */
    @Override
    public int  act(float deltaTime) { //  if path is null it sets it tries  to floatThing the owner to WoodWand given location one tile at actionTurns using the path it finds.
        LandSquareTile nextTile=placesToMove.get(0);
        if (placesToMove == null) {
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getScreenLocationx(), moveTile.getScreenLocationy(),0,0, xSize, ySize);
        }
        if (placesToMove.size <= 0) {// path is zero stop you are either done or can't get to the tile
            // stop movable is done moving
                movableComponent.stop();
            return -1;
        } else if (nextTile.isEnterable()==false || nextTile.getEntities(SolidObject.class).size>0) { // things can floatThing in the  way of the path  and if they do recalculate the path
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getLocationX(), moveTile.getLocationY(),0,0, xSize, ySize);
            if (placesToMove.size > 0) {
                calculateAndSetMoveDirection(position.getTileLocationX(), position.getTileLocationY(), nextTile.getLocationX(), nextTile.getLocationY());
                if (movableComponent.getLocationToMoveTo().equals(nextTile)) {
                    placesToMove.removeValue(movableComponent.getLocationToMoveTo(), false);
                }
            }
            else{
                movableComponent.stop();
                return -1;
            }
        }
        else{
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getScreenLocationx(), moveTile.getScreenLocationy(),0,0, xSize, ySize);
            if (placesToMove.size <= 0) {
                    movableComponent.stop();
                    return -1;
            }
            entity.add(new MoveToSingleTile());
            LandSquareTile tileToMoveTo= movableComponent.getLocationToMoveTo();
            if(tileToMoveTo.equals(nextTile)) {
                placesToMove.removeValue(tileToMoveTo, false);
            }
        }
        return placesToMove.size;
        }

    /**
     *
     * @param map
     * @param screenLocationXStart
     * @param screenLocationYStart
     * @param screenLocationXEnd
     * @param screenLocationYEnd
     * @param xStart
     * @param yStart
     * @param xMax
     * @param yMax
     * @return
     */
    public Array<LandSquareTile> pathFind(GameMap map,  float screenLocationXStart, float screenLocationYStart, float screenLocationXEnd, float screenLocationYEnd, int xStart, int yStart, int xMax, int yMax) { // calculates  the path finding method using the A* algorithm
        LandSquareTile tileFrom=map.screenToTile(screenLocationXStart, screenLocationYStart);
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        LandSquareTile tileTo=map.screenToTile(screenLocationXEnd, screenLocationYEnd);
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        int width=xMax-xStart;
        int height=yMax-yStart;
        Array<LandSquareTile> tiles= new Array<LandSquareTile>();
        if (movableComponent.isEightDirections()){
            Astar4 star = new Astar4(width,height,  map);
            IntArray paths=star.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        else {
            Astar8 star = new Astar8(width, height, map);
            IntArray paths=star.getPath(fromX, fromY, toX, toY);
            int size=paths.size;
            for (int count=size-1; count>=0; count=count-2){
                tiles.add(map.getMapSquare(paths.get(count-1), paths.get(count)));
            }
        }
        return tiles;
    }
    private LandSquareTile nodeToTile( GameMap map, Node node){ // actionTurns WoodWand node in to land square tile using  the x and y coordinates
        return map.getMapSquare(node.getxLocation(), node.getyLocation());
    }

    /**
     *Takes two tile and determines the direction one would have to travel to get from
     * tile one to tile two then  set the Characters moveable component to that direction;
     * @param tile the tile you are traveling from
     * @param tile2 the tile you are you traveling to
     * @return
     */
    public  void calculateAndSetMoveDirection(int tileX, int tileY, int tile2X, int tile2Y) {
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
        return ;
    }
}
