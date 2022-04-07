package com.jessematty.black.tower.Components;


import com.badlogic.ashley.core.Component;

public class Target implements Component {

    private float screenLocationX;
    private float screenLocationY;
    private String mapId;


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

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
}
