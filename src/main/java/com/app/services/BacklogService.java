package com.app.services;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.dto.projectManagement.BacklogDetailsDTO;
import com.app.exceptions.BacklogAlreadyExistsException;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UnvalidProgressValueException;
import com.app.models.Backlog;

import java.util.List;
import java.util.Optional;

public interface BacklogService {
    boolean add(BacklogDTO backlogDTO) throws ProjectNotFoundException, BacklogAlreadyExistsException;
    boolean delete(Long id);
    boolean update(Long id, BacklogDTO backlogDTO) throws  BacklogAlreadyExistsException, BacklogNotFoundException, ProjectNotFoundException, UnvalidProgressValueException;
    Optional<BacklogDTO> getBacklog(Long id);
    List<BacklogDetailsDTO> getAllBacklogsByProject(Long projectId);
}
