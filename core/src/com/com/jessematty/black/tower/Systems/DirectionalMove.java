package com.jessematty.black.tower.Systems;

import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import java.util.ArrayList;

public class DirectionalMove extends MoveOnGroundSystem {
    LandSquareTile moveTile;
    ArrayList<LandSquareTile> placesToMove;

    public DirectionalMove(MapDraw draw) {
        super(draw);
    }





}
