package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;

public class MapLoadingExeception extends Exception {

    public MapLoadingExeception() {
    }

    public MapLoadingExeception(String message) {
        super(message);
    }

    public MapLoadingExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public MapLoadingExeception(Throwable cause) {
        super(cause);
    }


}
