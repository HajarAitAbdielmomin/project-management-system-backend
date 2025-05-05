package com.app.services;

import com.app.dto.UserRelatedFeature.UserDTO;
import com.app.exceptions.UserNotFoundException;

import java.util.Optional;
import java.util.List;
public interface UserService {
    /**
     * Partially updates a user.
     *
     * @param userDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserDTO> updateUser(UserDTO userDTO, Long userId);

    /**
     * Get all the users.
     *
     * @return the list of entities.
     */
    List<UserDTO> getAllUsers();

    /**
     * Get the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDTO> getUser(Long id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    boolean delete(Long id);
}
