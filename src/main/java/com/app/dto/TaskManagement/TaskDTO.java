package com.app.dto.TaskManagement;

import com.app.enums.TaskStatus;
import com.app.models.Task;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
@Value
public class TaskDTO implements Serializable {
    Long id;
    String title;
    String description;
    TaskStatus status;
    Long memberId;
    Long backlogId;
}