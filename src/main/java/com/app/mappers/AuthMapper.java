package com.app.mappers;

import com.app.dto.SignupRequestDTO;
import com.app.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User  loginRequestDTOToUser(String username, String password);
    User SignupRequestDTOToUser(SignupRequestDTO signupRequest);
}
