package com.jessematty.black.tower.GameBaseClasses.Serialization;

public class LoadingException extends Exception {

    public LoadingException() {
    }

    public LoadingException(String message) {
        super(message);
    }

    public LoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingException(Throwable cause) {
        super(cause);
    }


}
