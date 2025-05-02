package com.app.mappers.implementation;

import com.app.dto.*;
import com.app.mappers.AuthMapper;
import com.app.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.processing.Generated;


@Slf4j
@Component("AuthMapperImpl")
public class AuthMapperImpl implements AuthMapper {
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
