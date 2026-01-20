package com.app.exceptions;

public class BacklogAlreadyExistsException extends RuntimeException {
    public BacklogAlreadyExistsException(String message) {
        super(message);
    }
}
