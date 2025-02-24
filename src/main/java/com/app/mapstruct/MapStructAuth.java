package com.app.mapstruct;

import com.app.dto.SignupRequestDTO;
import com.app.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructAuth {
    User  loginRequestDTOToUser(String username, String password);
    User SignupRequestDTOToUser(SignupRequestDTO signupRequest);
}
