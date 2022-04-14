package com.jessematty.black.tower.Maps.Buildings.Shops;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public class BuildingInfo { //Class for retururning BuildingMap info
    ArrayList <TiledMapTileLayer> layers;
    int []  enterenceX;
    int []  enterenceY;

    int terrainOuterType;

    public BuildingInfo(ArrayList<TiledMapTileLayer> layers, int []  enterenceX, int []  enterenceY, int terrainOuterType) {
        this.layers = layers;
        this.enterenceX = enterenceX;
        this.enterenceY = enterenceY;
        this.terrainOuterType = terrainOuterType;

    }


    public ArrayList<TiledMapTileLayer> getLayers() {
        return layers;
    }


    public int [] getEnterenceX() {
        return enterenceX;
    }

    public int [] getEnterenceY() {
        return enterenceY;
    }

    public int getTerrainOuterType() {
        return terrainOuterType;
    }
}
