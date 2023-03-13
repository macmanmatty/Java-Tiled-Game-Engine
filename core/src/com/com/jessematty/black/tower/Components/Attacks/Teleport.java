package com.jessematty.black.tower.Components.Attacks;

import com.badlogic.ashley.core.Component;


public class Teleport implements Component {


    private int tileTransportLocationX;
    private int tileTransportLocationY;
    private int mapTransportLocationX;
    private int mapTransportLocationY;

    public int getTileTransportLocationX() {
        return tileTransportLocationX;
    }

    public void setTileTransportLocationX(int tileTransportLocationX) {
        this.tileTransportLocationX = tileTransportLocationX;
    }

    public int getTileTransportLocationY() {
        return tileTransportLocationY;
    }

    public void setTileTransportLocationY(int tileTransportLocationY) {
        this.tileTransportLocationY = tileTransportLocationY;
    }

    public int getMapTransportLocationX() {
        return mapTransportLocationX;
    }

    public void setMapTransportLocationX(int mapTransportLocationX) {
        this.mapTransportLocationX = mapTransportLocationX;
    }

    public int getMapTransportLocationY() {
        return mapTransportLocationY;
    }

    public void setMapTransportLocationY(int mapTransportLocationY) {
        this.mapTransportLocationY = mapTransportLocationY;
    }
}

