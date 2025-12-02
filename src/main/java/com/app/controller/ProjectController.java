package com.app.controller;

import com.app.dto.projectManagement.ProjectDTO;
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
}
