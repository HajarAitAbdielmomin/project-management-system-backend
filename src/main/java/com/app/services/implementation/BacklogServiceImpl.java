package com.app.services.implementation;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.dto.projectManagement.BacklogDetailsDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UnvalidProgressValueException;
import com.app.mappers.BacklogMapper;
import com.app.repository.BacklogRepository;
import com.app.repository.ProjectRepository;
import com.app.services.BacklogService;

import java.util.List;
import java.util.Optional;

public class BacklogServiceImpl implements BacklogService {
    BacklogRepository backlogRepository;
    ProjectRepository projectRepository;
    BacklogMapper backlogMapper;

    @Override
    public boolean add(BacklogDTO backlogDTO) throws ProjectNotFoundException{
        return false;
    }

    @Override
    public boolean delete(Long id){
        return false;
    }

    @Override
    public boolean update(Long id, BacklogDTO backlogDTO) throws BacklogNotFoundException, ProjectNotFoundException, UnvalidProgressValueException
    {
        return false;
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
