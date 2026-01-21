package com.app.controller;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.exceptions.BacklogAlreadyExistsException;
import com.app.exceptions.BacklogNotFoundException;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UnvalidProgressValueException;
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
    private final BacklogServiceImpl backlogService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('project manager')")
    public ResponseEntity<?> createBacklog(@Valid @RequestBody BacklogDTO backlogDTO)
        throws ProjectNotFoundException, BacklogAlreadyExistsException {

        return backlogService.add(backlogDTO) ?
                ResponseEntity.ok().body("Backlog created successfully") :
                ResponseEntity.badRequest().body("Backlog creation failed");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBacklog(@PathVariable Long id) {
        return backlogService.delete(id) ?
                ResponseEntity.ok().body("Backlog deleted successfully") :
                ResponseEntity.badRequest().body("Backlog deletion failed");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBacklog(@PathVariable Long id, @Valid @RequestBody BacklogDTO backlogDTO)
            throws ProjectNotFoundException, BacklogNotFoundException, UnvalidProgressValueException, BacklogAlreadyExistsException
    {
        return backlogService.update(id, backlogDTO) ?
                ResponseEntity.ok().body("Backlog updated successfully") :
                ResponseEntity.badRequest().body("Backlog updating failed");
    }

}
