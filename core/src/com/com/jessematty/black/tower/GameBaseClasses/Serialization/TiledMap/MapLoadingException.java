package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;

/**
 * unchecked exception for loading tiled maps / game maps
 */
public class MapLoadingException extends RuntimeException {
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
