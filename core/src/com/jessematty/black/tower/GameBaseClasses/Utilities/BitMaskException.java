package com.jessematty.black.tower.GameBaseClasses.Utilities;

public class BitMaskException extends Exception {

    public BitMaskException() {
    }

    public BitMaskException(String message) {
        super(message);
    }

    public BitMaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public BitMaskException(Throwable cause) {
        super(cause);
    }

    public BitMaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
