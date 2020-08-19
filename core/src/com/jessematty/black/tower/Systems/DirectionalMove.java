package com.jessematty.black.tower.Systems;

import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;

public class DirectionalMove extends MoveOnGroundSystem {
    LandSquareTile moveTile;
    ArrayList<LandSquareTile> placesToMove;

    public DirectionalMove(MapDraw draw) {
        super(draw);
    }


    @Override
    public boolean move() { // moves the owner to new tile area

        return true;




        }



}
