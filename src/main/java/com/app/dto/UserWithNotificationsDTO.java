package com.app.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.app.models.User}
 */
@Value
public class UserWithNotificationsDTO implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Set<Long> notificationsIds;
}