package com.app.dto.teamManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.app.models.Team}
 */
@Value
public class TeamDTO implements Serializable {
    @Size(message = "Team name must be between 2 and 100 characters", min = 2, max = 100)
    @NotBlank(message = "Team name is required")
    String name;
    Long projectManagerId;
    @NotEmpty(message = "Team must have at least one member")
    List<Long> membersId;
    Long projectId;
}