package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;

import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

public class TiledMapTileChangable extends AtlasAnimatedTiledMapTile {

    int layer;
    int locationX;
    int locationY;

    public TiledMapTileChangable(float interval, Array<AtlasStaticTiledMapTile> frameTiles) {
        super(interval, frameTiles);
    }

    public TiledMapTileChangable(int [] intervals, AtlasStaticTiledMapTile [] frameTiles) {
        super(intervals, frameTiles);
    }

    public TiledMapTileChangable(AtlasAnimatedTiledMapTile other) {
        super(other);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}
