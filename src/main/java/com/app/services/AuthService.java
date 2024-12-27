package com.app.services;


import com.app.dto.JwtResponse;
import com.app.dto.LoginRequestDTO;
import com.app.exceptions.UserNotFoundException;

public interface AuthService {
    public JwtResponse authenticateUser(LoginRequestDTO loginRequest)
            throws UserNotFoundException;
}
