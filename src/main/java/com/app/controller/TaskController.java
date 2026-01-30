package com.app.controller;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.TaskAlreadyExistsException;
import com.app.exceptions.TaskNotFoundException;
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
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole(project manager)")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return taskService.delete(id) ?
                ResponseEntity.ok().body("Task deleted successfully") :
                ResponseEntity.badRequest().body("Task deletion failed");
    }
    @PatchMapping("/update/{id}")
    //@PreAuthorize("hasRole(project manager)")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO)
    throws TaskAlreadyExistsException, UserNotFoundException, TaskNotFoundException
    {
        return taskService.update(id, taskDTO) ?
                ResponseEntity.ok().body("Task updated successfully") :
                ResponseEntity.badRequest().body("Task updating failed");
    }
    @GetMapping("/get/{id}")
    //@PreAuthorize("hasRole(project manager)")
    public ResponseEntity<?> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @GetMapping("/get/all/{backlogId}")
    //@PreAuthorize("hasRole(project manager)"))
    public ResponseEntity<?> getTasksByBacklog(@PathVariable Long backlogId){
        return ResponseEntity.ok(taskService.getTasksByBacklog(backlogId));
    }
}
