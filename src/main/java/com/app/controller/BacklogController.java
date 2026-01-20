package com.app.controller;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.exceptions.BacklogAlreadyExistsException;
import com.app.exceptions.ProjectNotFoundException;
import com.app.services.implementation.BacklogServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/backlogs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BacklogController {
    BacklogServiceImpl backlogService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('project manager')")
    public ResponseEntity<?> createBacklog(@Valid @RequestBody BacklogDTO backlogDTO)
        throws ProjectNotFoundException, BacklogAlreadyExistsException {

        return backlogService.add(backlogDTO) ?
                ResponseEntity.ok().body("Backlog created successfully") :
                ResponseEntity.badRequest().body("Backlog creation failed");
    }
}
