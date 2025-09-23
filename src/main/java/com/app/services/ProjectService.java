package com.app.services;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.models.Project;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ProjectService {
    Optional<Project> add(ProjectDTO projectDTO) throws UserNotFoundException;
}
