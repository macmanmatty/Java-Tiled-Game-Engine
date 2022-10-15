package com.jessematty.black.tower.Systems;

import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

public class Follow extends MoveOnGroundSystem {
int distance;
int moves;

    public Follow(MapDraw draw) {
        super(draw);
    }

/*
Movable moveableToFollow;
Direction orientation;
    ArrayList<LandSquareTile> placesToMove;

boolean move;

    public Follow(Movable movable, Movable moveableToFollow) {
        super(movable);
       this.moveableToFollow=moveableToFollow;
       this.distance=distance;
       this.orientation=orientation;
       frames=1;



    }

    @Override
    public boolean move() {



        LandSquareTile location=map.getPreviousTile(moveableToFollow.getLocationToTransportTo(), moveableToFollow.getDirection(),2);


            placesToMove = pathFind(movable.getLocationToTransportTo(), location, 0, 0, xSize, ySize);



        if (placesToMove.size() <= 0) {// path is zero stop you are either done or can't get to the tile


            movable.stop();

            return true;


            }



        else if (placesToMove.get(0).isEnterable()==false || placesToMove.get(0).hasTraps()==true || placesToMove.get(0).hasOccupants()) { // things can floatThing in the  way of the path  and if they do recalculate the path

            placesToMove = pathFind(movable.getLocationToTransportTo(), location, 0, 0, xSize, ySize);
            if (placesToMove.size() > 0) {
                new MoveToSingleTileSystem(movable, placesToMove.get(0)).move();
                if (movable.getLocationToTransportTo().equals(placesToMove.get(0))) {
                    placesToMove.removeEntity(0);
                }

            }
            else{

                movable.stop();

                return true;

            }


        }



            new MoveToSingleTileSystem(movable, placesToMove.get(0)).move();
            if(movable.getLocationToTransportTo().equals(placesToMove.get(0))) {
                placesToMove.removeEntity(0);
            }







        return false;




    }


    @Override
    public int getHashCode() {
        return 113;
    }

    */
}
