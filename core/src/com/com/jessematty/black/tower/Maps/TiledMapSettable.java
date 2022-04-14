package com.jessematty.black.tower.Maps;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 *  interface for objects that get and set game map object
 * @param <Map> the generic type of map to set
 */
public interface TiledMapSettable<Map extends TiledMap > {
     void setMap(Map gameMap);
    TiledMap getMap();

}
