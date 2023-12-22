package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity;
/**
 * unchecked exception for loading entities
 */
public class EntityLoadingException extends RuntimeException {
    public EntityLoadingException() {
    }
    public EntityLoadingException(String message) {
        super(message);
    }
    public EntityLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityLoadingException(Throwable cause) {
        super(cause);
    }
}
