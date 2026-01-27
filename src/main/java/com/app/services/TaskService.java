package com.app.services;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.dto.TaskManagement.TaskDetailsDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.TaskAlreadyExistsException;
import com.app.exceptions.TaskNotFoundException;
import com.app.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    boolean add(TaskDTO taskDTO) throws BacklogNotFoundException, TaskAlreadyExistsException, UserNotFoundException;
    boolean delete(Long id);
    boolean update(Long id, TaskDTO taskDTO) throws TaskNotFoundException, UserNotFoundException;
    Optional<TaskDetailsDTO> getTask(Long id);
    Long getTasksCountByBacklogId(Long backlogId);
    Long getTasksCountByMemberId(Long memberId);
    List<TaskDetailsDTO> getTasksByBacklog(Long backlogId);
}
