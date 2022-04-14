package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

public interface NamedTiledMapTile  extends TiledMapTile {

    public void  setNames(String[] names);
    String [] getNames();


}



