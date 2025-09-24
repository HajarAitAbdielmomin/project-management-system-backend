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
import com.app.models.*;
import com.app.repository.*;
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
import java.util.Map;
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
    private final ProjectManagerRepository projectManagerRepository;
    private final ProductOwnerRepository productOwnerRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public JwtResponse authenticateUser(LoginRequestDTO loginRequest)
            throws UserNotFoundException
    {

        if(!userRepository.existsByEmail(loginRequest.getEmail()))
            throw new UserNotFoundException("User not found");

        User user = loginRequestDTOMapper.toEntity(loginRequest);
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

                Map<String, ERole> roleMapping = Map.of(
                        "admin", ERole.ROLE_ADMIN,
                        "team member", ERole.ROLE_TEAM_MEMBER,
                        "product owner", ERole.ROLE_PRODUCT_OWNER,
                        "project manager", ERole.ROLE_PROJECT_MANAGER
                );

                ERole roleEnum = roleMapping.getOrDefault(role, ERole.ROLE_USER);
                Role resolvedRole = roleRepository.findByName(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(resolvedRole);


            });
        }

        user.setRoles(roles);
        String userRole = getUserRole(roles);

        switch (userRole) {
            case "ROLE_PROJECT_MANAGER" ->
                projectManagerRepository.save(
                        ProjectManager.builder()
                                .id(user.getId())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .createdAt(user.getCreatedAt())
                                .updatedAt(user.getUpdatedAt())
                                .roles(user.getRoles())
                                .build()
                );

            case "ROLE_PRODUCT_OWNER" ->
                productOwnerRepository.save(
                        ProductOwner.builder()
                                .id(user.getId())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .createdAt(user.getCreatedAt())
                                .updatedAt(user.getUpdatedAt())
                                .roles(user.getRoles())
                                .build()
                );

            case "ROLE_TEAM_MEMBER" ->
               teamMemberRepository.save(
                       TeamMember.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .roles(user.getRoles())
                        .build()
                );
            default -> userRepository.save(user);

        }

        return true;
    }

    private String getUserRole(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .filter(roleName -> roleName.contains("ROLE_ADMIN") || roleName.contains("ROLE_PROJECT_MANAGER") || roleName.contains("ROLE_PRODUCT_OWNER") || roleName.contains("ROLE_TEAM_MEMBER") )
                .findFirst()
                .orElse("ROLE_USER");
    }

}
