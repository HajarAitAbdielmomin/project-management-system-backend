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
public class NotificationDto implements Serializable {
    Long id;
    String text;
    LocalDateTime createdAt;
    Set<Long> usersIds;
}