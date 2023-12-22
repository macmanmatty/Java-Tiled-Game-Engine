package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity;
/**
 * checked exception for loading entities
 */
public class EntityGenerationException extends Exception {
    public EntityGenerationException() {
    }
    public EntityGenerationException(String message) {
        super(message);
    }
    public EntityGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityGenerationException(Throwable cause) {
        super(cause);
    }
}
