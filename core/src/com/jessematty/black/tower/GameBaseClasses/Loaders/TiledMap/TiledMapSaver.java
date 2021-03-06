package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public interface TiledMapSaver {
    TiledMap loadMap(GameAssets assetts);
    void saveMap(TiledMap tiledMap, String atlasName);

}
