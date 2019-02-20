package org.brutus.exception.language.execute;

import org.brutus.exception.BrutusException;

public class ExecutionFailedException extends BrutusException {

    public ExecutionFailedException() {
    }

    public ExecutionFailedException(String message) {
        super(message);
    }

    public ExecutionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutionFailedException(Throwable cause) {
        super(cause);
    }

    public ExecutionFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
