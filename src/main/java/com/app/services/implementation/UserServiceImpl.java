package com.app.services.implementation;

import com.app.dto.UserRelatedFeature.UserDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.UserMapper;
import com.app.models.User;
import com.app.repository.UserRepository;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO, Long userId) {
      User user = userRepository.findById(userId).orElse(null);
      User updatedUser = userMapper.partialUpdate(userDTO, user);

      //Handle password update
      if(userDTO.getPassword() != null) {
          updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      }

      return Optional.ofNullable(userMapper.toDto(userRepository.save(updatedUser)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public Optional<UserDTO> getUser(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
