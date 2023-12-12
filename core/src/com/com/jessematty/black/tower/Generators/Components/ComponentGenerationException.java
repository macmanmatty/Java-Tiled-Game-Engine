package com.jessematty.black.tower.Generators.Components;

public class ComponentGenerationException extends RuntimeException {

    public ComponentGenerationException() {
    }

    public ComponentGenerationException(String message) {
        super(message);
    }

    public ComponentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentGenerationException(Throwable cause) {
        super(cause);
    }

    public ComponentGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
