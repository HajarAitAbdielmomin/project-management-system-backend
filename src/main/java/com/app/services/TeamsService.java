package com.app.services;

import com.app.dto.UserRelatedFeature.TeamMemberDTO;
import com.app.dto.teamManagement.TeamDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.TeamNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.models.TeamMember;

import java.util.List;
import java.util.Optional;

public interface TeamsService {
    boolean delete(Long id);

    boolean add(TeamDTO teamDto) throws UserNotFoundException, ProjectNotFoundException;

    boolean update(Long id, TeamDTO teamInputDto) throws TeamNotFoundException, UserNotFoundException, ProjectNotFoundException;

    Optional<TeamDTO> get(Long id);

    Optional<List<TeamDTO>> getAllByProjectManager(Long id);

    Optional<List<TeamMemberDTO>> getMembersByTeam(Long id);
}
