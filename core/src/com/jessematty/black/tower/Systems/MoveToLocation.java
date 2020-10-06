package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Calculators.PathFind.Astar4;
import com.jessematty.black.tower.GameBaseClasses.Calculators.PathFind.Astar8;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.MoveToSingleTile;
import com.jessematty.black.tower.Components.SolidObject;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;

public class MoveToLocation extends GameEntitySystem {
   private LandSquareTile moveTile;
   private  ArrayList<LandSquareTile> placesToMove;
  private PositionComponent position;
  private GameMap map;
   private  int xSize;
    private int ySize;
   private  Movable movable;
   private Entity entity;



    public MoveToLocation( GameMap map , MapDraw draw, Entity entity) {
        super(draw);
        this.xSize=map.getXSize();
        this.ySize=map.getYSize();
        this.map=map;
        this.entity=entity;


    }


    @Override
    public void update(float deltaTime) { //  if path is null it sets it tries  to floatThing the owner to WoodWand given location one tile at actionTurns using the path it finds.

        if (placesToMove == null) {
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getScreenLocationx(), moveTile.getScreenLocationy(),0,0, xSize, ySize);
        }


        if (placesToMove.size() <= 0) {// path is zero stop you are either done or can't get to the tile
            // stop movable is done moving
                movable.stop();




            return;

        } else if (placesToMove.get(0).isEnterable()==false || placesToMove.get(0).getEntities(SolidObject.class).size>0) { // things can floatThing in the  way of the path  and if they do recalculate the path

            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getScreenLocationx(), moveTile.getScreenLocationy(),0,0, xSize, ySize);
            if (placesToMove.size() > 0) {
                entity.add(new MoveToSingleTile());
                if (movable.getLocationToMoveTo().equals(placesToMove.get(0))) {
                    placesToMove.remove(movable.getLocationToMoveTo());
                }

            }
            else{
                movable.stop();

                return;

            }


        }
        else{
            placesToMove = pathFind(map,position.getLocationX() ,position.getLocationY() ,moveTile.getScreenLocationx(), moveTile.getScreenLocationy(),0,0, xSize, ySize);
            if (placesToMove.size() <= 0) {


                    movable.stop();
                    return;



            }


            entity.add(new MoveToSingleTile());
            LandSquareTile tileToMoveTo=movable.getLocationToMoveTo();
            if(tileToMoveTo.equals(placesToMove.get(0))) {
                placesToMove.remove(tileToMoveTo);
            }
        }






        return;




        }








    public ArrayList<LandSquareTile> pathFind(GameMap map,  float screenLocationXStart, float screenLocationYStart, float screenLocationXEnd, float screenLocationYEnd, int xStart, int yStart, int xMax, int yMax) { // calculates  the path finding method using the A* algorithm

        LandSquareTile tileFrom=map.screenToTile(screenLocationXStart, screenLocationYStart);
        int fromX=tileFrom.getLocationX();
        int fromY=tileFrom.getLocationY();
        LandSquareTile tileTo=map.screenToTile(screenLocationXEnd, screenLocationYEnd);
        int toY=tileTo.getLocationY();
        int toX=tileTo.getLocationX();
        int width=xMax-xStart;
        int height=yMax-yStart;
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();

        if (movable.isEightDirections()){
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

}
