package com.app.dto.projectManagement;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.app.models.Project}
 */
@Value
public class ProjectDTO implements Serializable {
    String title;
    String description;
    Long projectManagerId;
    Long productOwnerId;
    Set<Long> backlogsIds;
}