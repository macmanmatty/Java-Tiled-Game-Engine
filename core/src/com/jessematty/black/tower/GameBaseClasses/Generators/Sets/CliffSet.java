package com.jessematty.black.tower.GameBaseClasses.Generators.Sets;

import java.util.HashMap;

public class CliffSet extends TileSet {

    int minWidth=2;
    HashMap<Integer, Integer> tileWidths = new HashMap<Integer, Integer>();
    int minHeight=3;
    boolean heightIncrease=true;
    boolean [] enterenceDirections = new boolean [4];
    


    public CliffSet() {



    }

    @Override
    public void setTileNumbers() {
        super.setTileNumbers();
        tileWidths.put(11, 1) ;
        tileWidths.put(22,1);
        tileWidths.put(31,1);
        tileWidths.put(104,1);
        tileWidths.put(208,1);
        tileWidths.put(127,1);
        tileWidths.put(254,1);
        tileWidths.put(251, 1);
        tileWidths.put(223,1);
        tileWidths.put(256,1);





    }

    public int getMinWidth() {
        return minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public boolean isHeightIncrease() {
        return heightIncrease;
    }

    public void setHeightIncrease(boolean heightIncrease) {
        this.heightIncrease = heightIncrease;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }


    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getTileWidth(int bitNumber){


       int width= tileWidths.get(bitNumber);


       if(width>0) {
           return width;
       }


       return 1;




    }


}


