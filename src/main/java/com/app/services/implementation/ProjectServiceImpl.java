package com.app.services.implementation;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.dto.projectManagement.ProjectDetailsDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.ProjectDetailsMapper;
import com.app.mappers.ProjectMapper;
import com.app.models.ProductOwner;
import com.app.models.Project;
import com.app.models.ProjectManager;
import com.app.models.Team;
import com.app.repository.ProjectManagerRepository;
import com.app.repository.ProductOwnerRepository;
import com.app.repository.ProjectRepository;
import com.app.repository.TeamRepository;
import com.app.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectManagerRepository projectManagerRepository;
    private final ProductOwnerRepository projectOwnerRepository;
    private final ProjectDetailsMapper projectDetailsMapper;

    @Override
    public boolean add(ProjectDTO projectDTO) throws UserNotFoundException {

        Project project = projectMapper.toEntity(projectDTO);

        if (project == null) return false;

        ProductOwner productOwner = projectOwnerRepository.findById(projectDTO.getProductOwnerId())
                .orElseThrow(() -> new UserNotFoundException("Product Owner not found"));

        ProjectManager projectManager = projectManagerRepository.findById(projectDTO.getProjectManagerId())
                .orElseThrow(() -> new UserNotFoundException("Project Manager not found"));


        project.setProductOwner(productOwner);
        project.setProjectManager(projectManager);


        projectRepository.save(project);

        return true;
    }
    @Override
    public Optional<Project> update(Long id, ProjectDTO projectDTO) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            Project projectToUpdate = project.get();
            projectToUpdate.setTitle(projectDTO.getTitle());
            projectToUpdate.setDescription(projectDTO.getDescription());
            return Optional.of(projectRepository.save(projectToUpdate));
        } else return Optional.empty();

    }
    @Override
    public boolean delete(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        project.ifPresent(projectRepository::delete);
        return project.isPresent();
    }
    @Override
    public Optional<ProjectDetailsDTO> getProjectDetails(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();

        return Optional.of(projectDetailsMapper.toDto(project));
    }
    @Override
    public Optional<List<ProjectDetailsDTO>> getAllProjectsByUser(Long id) {
        Optional<List<Project>> projects = projectRepository.findAllByProjectManagerId(id);
        return projects.map(projectList
                -> projectList.stream().map(projectDetailsMapper::toDto).toList());
    }



}
