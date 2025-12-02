package com.app.services.implementation;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.ProjectMapper;
import com.app.models.ProductOwner;
import com.app.models.Project;
import com.app.models.ProjectManager;
import com.app.repository.ProjectManagerRepository;
import com.app.repository.ProductOwnerRepository;
import com.app.repository.ProjectRepository;
import com.app.repository.UserRepository;
import com.app.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectManagerRepository projectManagerRepository;
    private final ProductOwnerRepository projectOwnerRepository;

    @Override
    public Optional<Project> add(ProjectDTO projectDTO) throws UserNotFoundException {

        Project project = projectMapper.toEntity(projectDTO);

        if (project == null) return Optional.empty();

        ProductOwner productOwner = projectOwnerRepository.findById(projectDTO.getProductOwnerId())
                .orElseThrow(() -> new UserNotFoundException("Product Owner not found"));

        ProjectManager projectManager = projectManagerRepository.findById(projectDTO.getProjectManagerId())
                .orElseThrow(() -> new UserNotFoundException("Project Manager not found"));


        project.setProductOwner(productOwner);
        project.setProjectManager(projectManager);


        Project savedProject = projectRepository.save(project);

        return Optional.of(savedProject);
    }
}
