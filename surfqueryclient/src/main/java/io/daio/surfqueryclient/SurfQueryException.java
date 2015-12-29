package io.daio.surfqueryclient;

public class SurfQueryException extends Exception {

    public SurfQueryException(String message) {
        super(message);
    }

    public SurfQueryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
