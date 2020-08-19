package com.jessematty.black.tower.Components;


import com.badlogic.ashley.core.Component;

public class Target implements Component {

    private float screenLocationX;
    private float screenLocationY;
    private int  mapTargetX;
    private int mapTargetY;

    public float getScreenLocationX() {
        return screenLocationX;
    }

    public void setScreenLocationX(float screenLocationX) {
        this.screenLocationX = screenLocationX;
    }

    public float getScreenLocationY() {
        return screenLocationY;
    }

    public void setScreenLocationY(float screenLocationY) {
        this.screenLocationY = screenLocationY;
    }

    public int getMapTargetX() {
        return mapTargetX;
    }

    public void setMapTargetX(int mapTargetX) {
        this.mapTargetX = mapTargetX;
    }

    public int getMapTargetY() {
        return mapTargetY;
    }

    public void setMapTargetY(int mapTargetY) {
        this.mapTargetY = mapTargetY;
    }
}
