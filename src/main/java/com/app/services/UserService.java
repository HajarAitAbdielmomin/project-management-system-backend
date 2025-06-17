package com.app.services;

import com.app.dto.UserRelatedFeature.UserDTO;
import com.app.exceptions.UserNotFoundException;

import java.util.Optional;
import java.util.List;
public interface UserService {
    Optional<UserDTO> updateUser(UserDTO userDTO, Long userId);
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUser(Long id);
    boolean delete(Long id);
}
