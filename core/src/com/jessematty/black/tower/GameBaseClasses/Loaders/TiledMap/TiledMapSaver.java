package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public interface TiledMapSaver {
    TiledMap loadMap(GameAssets assets);
    void saveMap(TiledMap tiledMap, String atlasName) throws MapLoadingExeception;
    MapProperties getMapProperties();

}
