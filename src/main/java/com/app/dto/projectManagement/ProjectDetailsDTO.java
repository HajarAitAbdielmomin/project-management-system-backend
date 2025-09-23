package com.app.dto.projectManagement;

import com.app.dto.UserRelatedFeature.ProjectManagerDTO;
import com.app.dto.UserRelatedFeature.TeamDTO;
import com.app.models.Project;

import lombok.Value;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * DTO for {@link Project}
 */
@Value
public class ProjectDetailsDTO implements Serializable {
    long id;
    String title;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime finishedAt;
    ProjectManagerDTO projectManager;
    TeamDTO teamDTO;
    List<BacklogDTO> backlogsDTO;
}