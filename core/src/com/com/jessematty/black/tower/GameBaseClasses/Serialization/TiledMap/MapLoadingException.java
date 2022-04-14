package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;

public class MapLoadingException extends Exception {

    public MapLoadingException() {
    }

    public MapLoadingException(String message) {
        super(message);
    }

    public MapLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapLoadingException(Throwable cause) {
        super(cause);
    }


}
