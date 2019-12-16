package com.epam.task1.exception;

public class DataBaseException extends Exception {
    public DataBaseException() {
    }

    public DataBaseException(final String message) {
        super(message);
    }

    public DataBaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(final Throwable cause) {
        super(cause);
    }
}
