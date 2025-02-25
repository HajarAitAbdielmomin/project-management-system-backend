package com.app.dto;

import com.app.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    String createdAt;
    String updatedAt;
}