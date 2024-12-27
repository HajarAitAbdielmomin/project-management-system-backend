package com.app.mapstruct;

import com.app.dto.LoginRequestDTO;
import com.app.dto.SignupRequestDTO;
import com.app.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    User  loginRequestDTOToUser(String username, String password);
    SignupRequestDTO userToSignupRequestDTO(User user);
}
