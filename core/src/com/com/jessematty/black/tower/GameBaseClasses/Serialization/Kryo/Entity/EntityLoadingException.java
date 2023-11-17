package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity;

public class EntityLoadingException extends Exception {

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
