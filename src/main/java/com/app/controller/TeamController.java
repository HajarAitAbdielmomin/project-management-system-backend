package com.app.controller;

import com.app.dto.UserRelatedFeature.TeamInputDto;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.services.implementation.TeamsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamsServiceImpl teamsService;

    @PostMapping("/create")
    //@PreAuthorize("hasRole('project manager')")
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamInputDto  teamInputDto)
    throws ProjectNotFoundException, UserNotFoundException {
        return teamsService.add(teamInputDto).isPresent() ?
                ResponseEntity.ok("Team created successfully") :
                ResponseEntity.badRequest().body("Team not created");
    }
}
