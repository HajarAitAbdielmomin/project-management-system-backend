package com.app.controller;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.TaskAlreadyExistsException;
import com.app.exceptions.UserNotFoundException;
import com.app.services.implementation.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole(project manager)")
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDTO taskDTO)
            throws TaskAlreadyExistsException, BacklogNotFoundException, UserNotFoundException
    {
        return taskService.add(taskDTO) ?
                ResponseEntity.ok().body("Task created successfully") :
                ResponseEntity.badRequest().body("Task creation failed");
    }
    @GetMapping("/backlog/{id}/count")
    //@PreAuthorize("hasRole(project manager)")
    public Long getTasksCountByBacklogId(@PathVariable Long backlogId){
        return taskService.getTasksCountByBacklogId(backlogId);
    }
    @GetMapping("/member/{id}/count")
    //@PreAuthorize("hasRole(project manager)")
    public Long getTasksCountByMemberId(@PathVariable Long memberId){
        return taskService.getTasksCountByMemberId(memberId);
    }
}
