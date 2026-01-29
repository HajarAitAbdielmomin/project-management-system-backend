package com.app.services.implementation;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.dto.TaskManagement.TaskDetailsDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.TaskAlreadyExistsException;
import com.app.exceptions.TaskNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.TaskDetailsMapper;
import com.app.mappers.TaskMapper;
import com.app.repository.BacklogRepository;
import com.app.repository.TaskRepository;
import com.app.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BacklogRepository backlogRepository;
    private final TaskMapper taskMapper;
    private final TaskDetailsMapper taskDetailsMapper;
    @Override
    public boolean add(TaskDTO taskDTO)
            throws BacklogNotFoundException, TaskAlreadyExistsException, UserNotFoundException {

        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Long id, TaskDTO taskDTO) throws TaskNotFoundException, UserNotFoundException {
        return false;
    }

    @Override
    public Optional<TaskDetailsDTO> getTask(Long id) {
        return Optional.empty();
    }

    @Override
    public Long getTasksCountByBacklogId(Long backlogId) {
        return 0L;
    }

    @Override
    public Long getTasksCountByMemberId(Long memberId) {
        return 0L;
    }

    @Override
    public List<TaskDetailsDTO> getTasksByBacklog(Long backlogId) {
        return List.of();
    }
}
