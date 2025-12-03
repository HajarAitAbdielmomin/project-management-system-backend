package com.app.services;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.dto.projectManagement.ProjectDetailsDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.models.Project;

import java.util.List;
import java.util.Optional;


public interface ProjectService {
    Optional<Project> add(ProjectDTO projectDTO) throws UserNotFoundException;
    Optional<ProjectDetailsDTO> getProjectDetails(Long id) throws ProjectNotFoundException;
    boolean delete(Long id) throws ProjectNotFoundException;
    Optional<List<ProjectDetailsDTO>> getAllProjectsByUser(Long id);
    Optional<Project> update(Long id, ProjectDTO projectDTO);
}
