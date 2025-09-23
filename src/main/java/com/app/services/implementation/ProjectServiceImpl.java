package com.app.services.implementation;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.ProjectMapper;
import com.app.models.Project;
import com.app.repository.ProjectRepository;
import com.app.repository.UserRepository;
import com.app.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Optional<Project> add(ProjectDTO projectDTO) throws UserNotFoundException {
        return null;
    }
}
