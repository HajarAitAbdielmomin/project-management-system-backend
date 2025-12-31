package com.app.controller;

import com.app.dto.teamManagement.TeamDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.services.implementation.TeamsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamsServiceImpl teamsService;

    @PostMapping("/create")
    //@PreAuthorize("hasRole('project manager')")
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamDTO teamDTO)
    throws ProjectNotFoundException, UserNotFoundException {
        return teamsService.add(teamDTO) ?
                ResponseEntity.ok("Team created successfully") :
                ResponseEntity.badRequest().body("Team not created");
    }
}
