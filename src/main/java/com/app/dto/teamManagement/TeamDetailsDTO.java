package com.app.dto.teamManagement;

import com.app.models.Project;
import com.app.models.TeamMember;
import com.app.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.app.models.Team}
 */

@Value
public class TeamDetailsDTO implements Serializable {
     long id;
     String name;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
     User projectManager;
     List<TeamMember> members;
     Project project;
}