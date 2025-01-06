package com.app.mapstruct;

import com.app.dto.LoginRequestDTO;
import com.app.dto.SignupRequestDTO;
import com.app.models.Role;
import com.app.models.User;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    User  loginRequestDTOToUser(String username, String password);
    User SignupRequestDTOToUser(SignupRequestDTO signupRequest);

}
