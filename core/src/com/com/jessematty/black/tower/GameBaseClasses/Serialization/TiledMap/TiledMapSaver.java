package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public interface TiledMapSaver {
    TiledMap loadMap(GameAssets assets);
    void saveMap(TiledMap tiledMap);
    MapProperties getMapProperties();

}
