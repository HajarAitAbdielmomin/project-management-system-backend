package com.app.mappers.implementation;

import com.app.dto.UserRelatedFeature.UserDTO;
import com.app.mappers.UserMapper;
import com.app.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Slf4j
@Component("UserMapperImpl")
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserDTO userDTO) {
        if(userDTO == null) return null;
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    @Override
    public UserDTO toDto(User user) {
        if(user == null) return null;
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                //.password(user.getPassword())  Don't include password in DTO
                .build();
    }

    @Override
    public User partialUpdate(UserDTO userDTO, User user) {
        if(userDTO == null || user == null) return null;
        if(userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if(userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if(userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if(userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        return user;
    }


}
