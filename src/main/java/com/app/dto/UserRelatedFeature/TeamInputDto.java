package com.app.dto.UserRelatedFeature;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.app.models.Team}
 */
@Builder
@Getter
public class TeamInputDto implements Serializable {
    @Size(message = "Team name must be between 2 and 100 characters", min = 2, max = 100)
    @NotBlank(message = "Team name is required")
    private final String name;
    private final Long projectManagerId;
    @NotEmpty(message = "Team must have at least one member")
    private final List<Long> membersId;
    private final Long projectId;
}
