package com.app.services;


import com.app.dto.LoginRequestDTO;

public interface AuthService {
    public boolean authenticateUser(LoginRequestDTO loginRequest);
}
