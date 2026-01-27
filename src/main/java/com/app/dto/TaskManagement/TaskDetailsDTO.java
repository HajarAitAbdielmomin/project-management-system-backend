package com.app.dto.TaskManagement;

import com.app.dto.UserRelatedFeature.TeamMemberDTO;
import com.app.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.app.models.Task}
 */
@AllArgsConstructor
@Getter
public class TaskDetailsDTO implements Serializable {
    private final Long id;
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime finishedAt;
    private final TeamMemberDTO member;
}