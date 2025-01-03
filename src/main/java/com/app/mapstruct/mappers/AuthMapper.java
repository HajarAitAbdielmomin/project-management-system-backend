package com.app.mapstruct.mappers;

import com.app.dto.*;
import com.app.enums.ERole;
import com.app.mapstruct.MapStructMapper;
import com.app.models.User;
import com.app.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.processing.Generated;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class AuthMapper implements MapStructMapper {
    @Override
    public User loginRequestDTOToUser(String username, String password) {
        if ( username == null || password == null ) {
            return null;
        }
        return User.builder()
                .email(username)
                .password(password)
                .build()
                ;
    }

    @Override
    public Set<Role> mapToRole(Set<String> roleStrings) {
        Set<Role> roles = new HashSet<>();

        if (roleStrings == null || roleStrings.isEmpty()) {
            Role userRole = new Role();
            userRole.setName(ERole.ROLE_USER);
            roles.add(userRole);
            return roles;
        }

        for (String roleString : roleStrings) {
            if (roleString != null) {
                    ERole eRole = ERole.valueOf(roleString.toUpperCase().trim());
                    Role role = new Role();
                    role.setName(eRole);
                    roles.add(role);
            }
    }
        return roles;
    }

    @Override
    public User SignupRequestDTOToUser(SignupRequestDTO signupRequest) {
        if ( signupRequest == null ) {
            return null;
        }
        return User.builder()
                .firstName( signupRequest.getFirstName() )
                .lastName( signupRequest.getLastName() )
                .password( signupRequest.getPassword() )
                .email( signupRequest.getEmail() )
                .roles(null)
                .build()
                ;
    }
}
