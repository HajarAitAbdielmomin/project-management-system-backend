package com.app.services;

import com.app.dto.UserRelatedFeature.TeamDTO;
import com.app.dto.UserRelatedFeature.TeamInputDto;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.TeamInputMapper;
import com.app.models.Team;

import java.util.List;
import java.util.Optional;

public interface TeamsService {
    boolean delete(Long id);

    Optional<Team> add(TeamInputDto teamInputDto) throws UserNotFoundException, ProjectNotFoundException;

    Optional<Team> update(Long id, TeamInputDto teamInputDto);

    Optional<TeamDTO> get(Long id);

    Optional<List<TeamDTO>> getAll();
}
