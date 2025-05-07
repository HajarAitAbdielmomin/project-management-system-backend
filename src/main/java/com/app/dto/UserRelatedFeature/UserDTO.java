package com.app.dto.UserRelatedFeature;

import com.app.models.User;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    String firstName;
    String lastName;
    String email;
    String password;
}