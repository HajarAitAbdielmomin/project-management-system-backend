package com.app.exceptions;

public class BacklogNotFoundException extends RuntimeException {
    public BacklogNotFoundException(String message) {
        super(message);
    }
}
