package com.app.dto.projectManagement;

import com.app.dto.UserRelatedFeature.ProductOwnerDTO;
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
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime finishedAt;
    ProjectManagerDTO projectManager;
    ProductOwnerDTO productOwner;
    //TeamDTO teamDTO;
    //List<BacklogDTO> backlogsDTO;
}