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
    String firstName;
    String lastName;
    String email;
    String password;
    Set<Long> notificationsIds;
}