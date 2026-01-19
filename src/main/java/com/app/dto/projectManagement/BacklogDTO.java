package com.app.dto.projectManagement;

import com.app.enums.TaskStatus;
import com.app.models.Backlog;
import lombok.Value;

import java.io.Serializable;
import java.util.*;

/**
 * DTO for {@link Backlog}
 */
@Value
public class BacklogDTO implements Serializable {
    String title;
    Long projectId;
    TaskStatus status;
    float progress;

}