package com.jessematty.black.tower.Maps;

import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Area extends com.jessematty.black.tower.Maps.LandMap { // class for an area of land to place  people and things  and item plants ect.

     protected  int xTileStart;
     protected  int yTileStart;
    protected  int xTileEnd;
    protected  int yTileEnd;
    protected String id;

    public Area() {
        id = UUID.randomUUID().toString();
    }

    public  void setUp() {
        this.xTileStart =1;
        this.yTileStart = 1;
        this.xTileEnd = 1;
        this.yTileEnd = 1;

    }

    public Area( int xTileStart, int yTileStart, int xTileEnd, int yTileEnd, boolean randomSets, ArrayList<TileSet> tileSets) {
        this.xTileStart = xTileStart;
        this.yTileStart = yTileStart;
        this.xTileEnd = xTileEnd;
        this.yTileEnd = yTileEnd;

        if(randomSets==true){

            Collections.shuffle(tileSets);
        }



    }





}
