package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;

/**
 * checked exception for loading tiled maps / game maps
 */
public class MapGenerationException extends Exception {
    public MapGenerationException() {
    }
    public MapGenerationException(String message) {
        super(message);
    }
    public MapGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
    public MapGenerationException(Throwable cause) {
        super(cause);
    }
}
