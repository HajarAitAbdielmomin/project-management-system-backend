package com.app.dto;

import com.app.models.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDTO implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    String createdAt;
    String updatedAt;
}