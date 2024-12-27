package com.app.services.implementation;

import com.app.dto.JwtResponse;
import com.app.dto.LoginRequestDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.mapstruct.mappers.AuthMapper;
import com.app.models.User;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.services.AuthService;
import com.app.util.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final Jwt jwt;
    private final AuthMapper authMapper;
    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, Jwt jwt, AuthMapper authMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwt = jwt;
        this.authMapper = authMapper;
    }
    @Override
    public JwtResponse authenticateUser(LoginRequestDTO loginRequest)
            throws UserNotFoundException
    {
        if(!userRepository.existsByEmail(loginRequest.getEmail()))
            throw new UserNotFoundException("User not found");

        User user = authMapper.loginRequestDTOToUser(loginRequest.getEmail(),loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwt.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.builder()
                .id(userDetails.getId())
                .token(jwt)
                .email(userDetails.getUsername())
                .roles(roles)
                .build();
    }
}
