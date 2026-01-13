package com.app.dto.UserRelatedFeature;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.models.TeamMember}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberDTO implements Serializable {
    long id;
    String firstName;
    String lastName;
    String email;
}