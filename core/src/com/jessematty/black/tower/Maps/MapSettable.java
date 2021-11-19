package com.jessematty.black.tower.Maps;

/**
 *  interface for objects that get and set game map object
 * @param <Map> the generic type of map to set
 */
public interface MapSettable <Map extends GameMap> {
     void setMap(Map gameMap);
     Map getMap();

}
