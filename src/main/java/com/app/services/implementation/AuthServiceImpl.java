package com.app.services.implementation;

import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.services.AuthService;
import com.app.util.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final Jwt jwt;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, Jwt jwt) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwt = jwt;
    }
    @Override
    public boolean authenticateUser(com.app.dto.LoginRequestDTO loginRequest) {
        return false;
    }

}
