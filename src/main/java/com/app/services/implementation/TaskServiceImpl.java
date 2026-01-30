package com.app.services.implementation;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.dto.TaskManagement.TaskDetailsDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.TaskAlreadyExistsException;
import com.app.exceptions.TaskNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.TaskDetailsMapper;
import com.app.mappers.TaskMapper;
import com.app.mappers.TeamMapper;
import com.app.models.Backlog;
import com.app.models.Task;
import com.app.models.TeamMember;
import com.app.repository.BacklogRepository;
import com.app.repository.TaskRepository;
import com.app.repository.TeamMemberRepository;
import com.app.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BacklogRepository backlogRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TaskMapper taskMapper;
    private final TaskDetailsMapper taskDetailsMapper;
    @Override
    public boolean add(TaskDTO taskDTO)
            throws BacklogNotFoundException, TaskAlreadyExistsException, UserNotFoundException {

        Task task = taskMapper.toEntity(taskDTO);

        if(task == null) return false;

        if(taskRepository.existsTaskByBacklog_IdAndTitle(taskDTO.getBacklogId(), task.getTitle()))
            throw new TaskAlreadyExistsException(String.format("Task with title %s already exists", task.getTitle()));

        Backlog backlog = backlogRepository.findById(taskDTO.getBacklogId()).orElseThrow(
                () -> new BacklogNotFoundException(String.format("Backlog with id %d not found", taskDTO.getBacklogId()))
        );

        TeamMember teamMember = teamMemberRepository.findById(taskDTO.getMemberId()).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %d not found", taskDTO.getMemberId()))
        );

        task.setBacklog(backlog);
        task.setMember(teamMember);

        taskRepository.save(task);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
        return task.isPresent();
    }

    @Override
    public boolean update(Long id, TaskDTO taskDTO) throws TaskAlreadyExistsException,TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task with id %d not found", id))
        );

        if(taskRepository.existsTaskByBacklog_IdAndTitleAndIdNot(taskDTO.getBacklogId(), task.getTitle(), id))
            throw new TaskAlreadyExistsException("Task already exists");

        Backlog backlog = backlogRepository.findById(taskDTO.getBacklogId()).orElseThrow(
                () -> new BacklogNotFoundException(String.format("Backlog with id %d not found", taskDTO.getBacklogId()))
        );

        TeamMember member = teamMemberRepository.findById(taskDTO.getMemberId()).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %d not found", taskDTO.getMemberId()))
        );

        Task updatedTask = taskMapper.partialUpdate(taskDTO, task);

        taskRepository.save(updatedTask);

        return true;
    }

    @Override
    public Optional<TaskDetailsDTO> getTask(Long id) {
        return taskRepository.findById(id).map(taskDetailsMapper::toDto);
    }

    @Override
    public Long getTasksCountByBacklogId(Long backlogId) {
        return taskRepository.countTasksByBacklogId(backlogId);
    }

    @Override
    public Long getTasksCountByMemberId(Long memberId) {
        return taskRepository.countTasksByMemberId(memberId);
    }

    @Override
    public List<TaskDetailsDTO> getTasksByBacklog(Long backlogId) {
        return List.of();
    }
}
