package com.jessematty.black.tower.Generators.Components;

/**
 * Unchecked exception  for loading components
 */
public class ComponentLoadingException extends RuntimeException {

    public ComponentLoadingException() {
    }

    public ComponentLoadingException(String message) {
        super(message);
    }

    public ComponentLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentLoadingException(Throwable cause) {
        super(cause);
    }

    public ComponentLoadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
