package com.app.controller;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.models.Project;
import com.app.services.implementation.ProjectServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@Valid @RequestBody ProjectDTO projectDTO)
            throws UserNotFoundException {
        return projectService.add(projectDTO).isPresent() ?
                ResponseEntity.ok("Project added successfully") :
                ResponseEntity.badRequest().body("Project not added");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) throws ProjectNotFoundException {
        return ResponseEntity.ok(projectService.getProjectDetails(id));

    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getAllProjects(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getAllProjectsByUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return projectService.delete(id) ?
                ResponseEntity.ok("Project deleted successfully") :
                ResponseEntity.badRequest().body("Project not found");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.update(id, projectDTO).isPresent() ?
                ResponseEntity.ok("Project updated successfully") :
                ResponseEntity.badRequest().body("Project not updated");
    }
}
