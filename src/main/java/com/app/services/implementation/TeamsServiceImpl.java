package com.app.services.implementation;

import com.app.dto.UserRelatedFeature.TeamDTO;
import com.app.dto.UserRelatedFeature.TeamInputDto;
import com.app.enums.ERole;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.UserAlreadyExistsException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.TeamInputMapper;
import com.app.models.*;
import com.app.repository.*;
import com.app.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {
    private final TeamRepository teamRepository;
    private final TeamInputMapper teamMapper;
    private final ProjectManagerRepository projectManagerRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final UserRepository userRepository;
    private final ProjectRepository  projectRepository;
    @Override
    public boolean delete(Long id) {
        return false;
    }
    @Override
    public Optional<Team> add(TeamInputDto teamInputDto) throws UserNotFoundException, ProjectNotFoundException {
    return null;
    }
    @Override
    public Optional<Team> update(Long id, TeamInputMapper teamInputMapper) {
        return null;
    }
    @Override
    public Optional<TeamDTO> get(Long id) {
        return null;
    }
    @Override
    public Optional<List<TeamDTO>> getAll() {
        return null;
    }
}
