package com.app.handler;

import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.UnvalidProgressValueException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BacklogExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleBacklogIdException(BacklogNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleProgressNotFoundException(UnvalidProgressValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
