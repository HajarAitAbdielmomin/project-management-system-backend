package com.app.services.implementation;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.dto.projectManagement.BacklogDetailsDTO;
import com.app.exceptions.BacklogAlreadyExistsException;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UnvalidProgressValueException;
import com.app.mappers.BacklogMapper;
import com.app.models.Backlog;
import com.app.models.Project;
import com.app.repository.BacklogRepository;
import com.app.repository.ProjectRepository;
import com.app.services.BacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BacklogServiceImpl implements BacklogService {
    private final BacklogRepository backlogRepository;
    private final ProjectRepository projectRepository;
    private final BacklogMapper backlogMapper;

    @Override
    public boolean add(BacklogDTO backlogDTO) throws ProjectNotFoundException, BacklogAlreadyExistsException {
        Backlog backlog = backlogMapper.toEntity(backlogDTO);

        if(backlog == null) return false;

        if(backlogRepository.existsBacklogByTitle(backlog.getTitle()))
            throw new BacklogAlreadyExistsException(String.format("Backlog with title %s already exists", backlog.getTitle()));

        Project project = projectRepository.findById(backlogDTO.getProjectId()).orElseThrow(
                () -> new ProjectNotFoundException(String.format("Project with id %d not found", backlogDTO.getProjectId())));

        backlog.setProject(project);

        backlogRepository.save(backlog);

        return true;
    }

    @Override
    public boolean delete(Long id){
        Optional<Backlog> backlog = backlogRepository.findById(id);
        backlog.ifPresent(backlogRepository::delete);
        return backlog.isPresent();
    }

    @Override
    public boolean update(Long id, BacklogDTO backlogDTO) throws BacklogAlreadyExistsException, BacklogNotFoundException, ProjectNotFoundException, UnvalidProgressValueException
    {

        Backlog backlog = backlogRepository.findById(id).orElseThrow(
                () -> new BacklogNotFoundException(String.format("Backlog with id %d not found", id))
        );

        if(backlogRepository.existsBacklogByTitleAndIdNot(backlogDTO.getTitle(),id))
            throw new BacklogAlreadyExistsException("Backlog already exists");


        Backlog updatedBacklog = backlogMapper.partialUpdate(backlogDTO, backlog);


        backlogRepository.save(updatedBacklog);

        return true;
    }

    @Override
    public Optional<BacklogDTO> getBacklog(Long id){
        return null;
    }

    @Override
    public List<BacklogDetailsDTO> getAllBacklogsByProject(Long projectId){
        return null;
    }
}
