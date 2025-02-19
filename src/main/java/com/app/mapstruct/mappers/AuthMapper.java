package com.app.mapstruct.mappers;

import com.app.dto.*;
import com.app.mapstruct.MapStructAuth;
import com.app.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.processing.Generated;


@Slf4j
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class AuthMapper implements MapStructAuth {
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
