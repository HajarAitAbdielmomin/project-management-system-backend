package com.app.services;


import com.app.dto.JwtResponse;
import com.app.dto.LoginRequestDTO;
import com.app.dto.SignupRequestDTO;
import com.app.exceptions.UserAlreadyExistsException;
import com.app.exceptions.UserNotFoundException;
import com.app.models.User;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthService {
    public JwtResponse authenticateUser(LoginRequestDTO loginRequest)
            throws UserNotFoundException;
    boolean registerUser(SignupRequestDTO signupRequestDTO)
            throws UserAlreadyExistsException;
    boolean logoutUser(String authHeader);
}
