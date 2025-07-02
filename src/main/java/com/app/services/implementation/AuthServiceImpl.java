package com.app.services.implementation;

import com.app.dto.JwtResponse;
import com.app.dto.LoginRequestDTO;
import com.app.dto.SignupRequestDTO;
import com.app.enums.ERole;
import com.app.exceptions.UserAlreadyExistsException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.LoginRequestMapper;
import com.app.mappers.SignupRequestMapper;
import com.app.models.Role;
import com.app.models.User;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.services.AuthService;
import com.app.util.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final Jwt jwt;
    private final LoginRequestMapper loginRequestDTOMapper;
    private final SignupRequestMapper signupRequestMapper;
    private final TokenBlacklistServiceImpl tokenBlacklistService;


    @Override
    public JwtResponse authenticateUser(LoginRequestDTO loginRequest)
            throws UserNotFoundException
    {
        if(!userRepository.existsByEmail(loginRequest.getEmail()))
            throw new UserNotFoundException("User not found");

        User user = loginRequestDTOMapper.toEntity(loginRequest);
        System.out.println("Retrieved user"+user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        //This allows other parts of the application to access the authenticated user's details.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate a JWT token for the authenticated user
        String jwt = this.jwt.generateJwtToken(authentication);

        //The identity of the principal being authenticated
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        //returning response containing the user's information and the generated JWT
        return JwtResponse.builder()
                .id(userDetails.getId())
                .token(jwt)
                .email(userDetails.getUsername())
                .roles(roles)
                .build();
    }
//zidi exception 3la 9bal msg annotations
    @Override
    public boolean registerUser(SignupRequestDTO signupRequestDTO) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(signupRequestDTO.getEmail()))
            throw new UserAlreadyExistsException("User already exists");

        User user= signupRequestMapper.toEntity(signupRequestDTO);
        user.setPassword(encoder.encode(user.getPassword()));

        Set<String> strRoles = signupRequestDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {

            strRoles.forEach(role -> {

                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "team member":
                        Role teamMemberRole = roleRepository.findByName(ERole.ROLE_TEAM_MEMBER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(teamMemberRole);
                        break;
                    case "product owner":
                        Role productOwnerRole = roleRepository.findByName(ERole.ROLE_PRODUCT_OWNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(productOwnerRole);
                        break;
                    case "project manager":
                        Role projectManagerRole = roleRepository.findByName(ERole.ROLE_PROJECT_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(projectManagerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }

            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean logoutUser(String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                tokenBlacklistService.blacklistToken(token);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error during logout", e);
        }

    }
}
