package com.app.controller;

import com.app.dto.LoginRequestDTO;
import com.app.dto.SignupRequestDTO;
import com.app.exceptions.UserAlreadyExistsException;
import com.app.exceptions.UserNotFoundException;
import com.app.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest)
            throws UserNotFoundException {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signupRequestDTO)
            throws UserAlreadyExistsException {
            authService.registerUser(signupRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

}
