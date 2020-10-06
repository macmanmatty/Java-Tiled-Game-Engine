package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.Generators.Sets.InsideTileSet;

public class BuildingSpecs extends MapSpecs {
    int minRoomX;
    int minRoomY;
    int maxRoomX;
    int maxRoomY;
    boolean darkDefault;
    InsideTileSet insideTileSet;
    int outerX;
    int outerY;
    boolean townOnly;




    public int getMinRoomX() {
        return minRoomX;
    }

    public void setMinRoomX(int minRoomX) {
        this.minRoomX = minRoomX;
    }

    public int getMinRoomY() {
        return minRoomY;
    }

    public void setMinRoomY(int minRoomY) {
        this.minRoomY = minRoomY;
    }

    public int getMaxRoomX() {
        return maxRoomX;
    }

    public void setMaxRoomX(int maxRoomX) {
        this.maxRoomX = maxRoomX;
    }

    public int getMaxRoomY() {
        return maxRoomY;
    }

    public void setMaxRoomY(int maxRoomY) {
        this.maxRoomY = maxRoomY;
    }
}
