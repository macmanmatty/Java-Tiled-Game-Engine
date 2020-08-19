package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;

import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class ObjectTiledMapTile extends AnimatedTiledMapTile {
    public ObjectTiledMapTile(float interval, Array<StaticTiledMapTile> frameTiles) {
        super(interval, frameTiles);
    }
}
