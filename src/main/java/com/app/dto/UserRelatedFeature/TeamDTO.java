package com.app.dto.UserRelatedFeature;

import com.app.models.Project;
import com.app.models.ProjectManager;
import com.app.models.TeamMember;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.app.models.Team}
 */
@Builder
@Getter
@Setter
public class TeamDTO implements Serializable {
    private final long id;
    private final String name;
    private final ProjectManager projectManager;
    private final List<TeamMember> members;
    private final Project project;
}