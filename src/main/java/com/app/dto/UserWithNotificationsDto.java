package com.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.app.models.User}
 */
@Value
public class UserWithNotificationsDto implements Serializable {
    Long id;
    @Size(message = "First name must be between 2 and 50 characters", min = 2, max = 50)
    @NotBlank(message = "First name is required")
    String firstName;
    @Size(message = "Last name must be between 2 and 50 characters", min = 2, max = 50)
    @NotBlank(message = "Last name is required")
    String lastName;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    String email;
    @Size(message = "Password must be at least 8 characters long", min = 8)
    @NotBlank(message = "Password is required")
    String password;
    Set<Long> notificationsIds;
}