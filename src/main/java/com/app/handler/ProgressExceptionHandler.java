package com.app.handler;

import com.app.exceptions.UnvalidProgressValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProgressExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleProgressNotFoundException(UnvalidProgressValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
