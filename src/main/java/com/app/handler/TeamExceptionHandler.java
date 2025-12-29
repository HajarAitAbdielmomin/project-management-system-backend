package com.app.handler;

import com.app.exceptions.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class TeamExceptionHandler {
   @ExceptionHandler(TeamNotFoundException.class)
   public ResponseEntity<String> handleTeamExceptionHandler(TeamNotFoundException ex) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
   }
}
