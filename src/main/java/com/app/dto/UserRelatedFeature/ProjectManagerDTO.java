package com.app.dto.UserRelatedFeature;

import com.app.models.ProjectManager;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ProjectManager}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManagerDTO implements Serializable {
    String firstName;
    String lastName;
    String email;
}
