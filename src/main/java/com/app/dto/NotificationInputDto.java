package com.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.app.models.Notification}
 */
@Value
public class NotificationInputDto implements Serializable {
    @Size(message = "Notification text cannot exceed 500 characters", max = 500)
    @NotBlank(message = "Notification text cannot be blank")
    String text;
    Set<Long> usersIds;
}