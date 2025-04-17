package com.app.dto;

import com.app.models.User;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
public class UserDTO implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String createdAt;
    String updatedAt;
}