package com.app.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.app.models.Notification}
 */
@Value
public class NotificationDTO implements Serializable {
    Long id;
    String text;
    LocalDateTime createdAt;
    Set<Long> usersIds;
}