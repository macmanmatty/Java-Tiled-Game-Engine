package com.jessematty.black.tower.GameBaseClasses.Loaders.MapLoaders;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MemoryEfficentTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.TiledMapSaver;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class SavedMap {
  private     MemoryEfficentTiledMapSaver tiledMapSaver;
  private GameMap map;
  private LandSquareTile [] [] tileMap;


    public MemoryEfficentTiledMapSaver getTiledMapSaver() {
        return tiledMapSaver;
    }

    public void setTiledMapSaver(MemoryEfficentTiledMapSaver tiledMapSaver) {
        this.tiledMapSaver = tiledMapSaver;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public LandSquareTile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(LandSquareTile[][] tileMap) {
        this.tileMap = tileMap;
    }
}
