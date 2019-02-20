package org.brutus.exception;

public abstract class BrutusException extends Exception {

    public BrutusException() {
    }

    public BrutusException(String message) {
        super(message);
    }

    public BrutusException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrutusException(Throwable cause) {
        super(cause);
    }

    public BrutusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
