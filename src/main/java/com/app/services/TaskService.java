package com.app.services;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.dto.TaskManagement.TaskDetailsDTO;
import com.app.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    boolean add(TaskDTO taskDTO) throws  UnvalidDurationValueException, BacklogNotFoundException, TaskAlreadyExistsException, UserNotFoundException;
    boolean delete(Long id);
    boolean update(Long id, TaskDTO taskDTO) throws UnvalidDurationValueException, TaskNotFoundException, UserNotFoundException, TaskAlreadyExistsException;
    Optional<TaskDetailsDTO> getTask(Long id);
    Long getTasksCountByBacklogId(Long backlogId);
    Long getTasksCountByMemberId(Long memberId);
    List<TaskDetailsDTO> getTasksByBacklog(Long backlogId);
    boolean isTaskCompletedOnTime(Long taskId);
    boolean endTask(Long id);
}
