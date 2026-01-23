package com.app.dto.projectManagement;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.enums.TaskStatus;
import com.app.models.Backlog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Backlog}
 */
@AllArgsConstructor
@Getter
public class BacklogDetailsDTo implements Serializable {
    private final Long id;
    private final String title;
    private final TaskStatus status;
    private final float progress;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime finishedAt;
    private final Set<TaskDTO> tasks;
}