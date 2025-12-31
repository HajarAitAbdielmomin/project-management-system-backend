package com.app.services.implementation;

import com.app.dto.UserRelatedFeature.TeamMemberDTO;
import com.app.dto.teamManagement.TeamDTO;
import com.app.exceptions.ProjectNotFoundException;
import com.app.exceptions.TeamNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.mappers.TeamMapper;
import com.app.mappers.UserMapper;
import com.app.models.*;
import com.app.repository.*;
import com.app.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final ProjectManagerRepository projectManagerRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final UserRepository userRepository;
    private final ProjectRepository  projectRepository;
    private final UserMapper userMapper;

    @Override
    public boolean add(TeamDTO teamDto) throws UserNotFoundException, ProjectNotFoundException {

        Team team = teamMapper.toEntity(teamDto);

        if(team == null) return false;

        Project project = projectRepository.findById(teamDto.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        ProjectManager projectManager = projectManagerRepository.findById(teamDto.getProjectManagerId())
                .orElseThrow(() -> new UserNotFoundException("Project Manager not found"));

        team.setProjectManager(projectManager);
        team.setProject(project);

        List<TeamMember> users = new ArrayList<>();
        for (Long userId : teamDto.getMembersId()) {
            TeamMember user = teamMemberRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("Team member not found"));
            user.setTeam(team);
            users.add(user);
        }
        team.setMembers(users);
        teamRepository.save(team);

        return true;
    }
    @Override
    public boolean update(Long id, TeamDTO teamInputDto) throws TeamNotFoundException,
            UserNotFoundException, ProjectNotFoundException{

        Team team  = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with id : "+id+" not found"));

        if (teamInputDto.getName() != null) {
            team.setName(teamInputDto.getName());
        }

        if (teamInputDto.getProjectManagerId() != null) {
            ProjectManager projectManager = projectManagerRepository.findById(teamInputDto.getProjectManagerId())
                    .orElseThrow(() -> new UserNotFoundException("Project Manager with id : "+id+" not found"));
            team.setProjectManager(projectManager);
        }

        if (teamInputDto.getMembersId() != null) {
            List<TeamMember> users = new ArrayList<>();
            for (Long userId : teamInputDto.getMembersId()) {
                TeamMember user = teamMemberRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("Team member with id : "+id+" not found"));
                user.setTeam(team);
                users.add(user);
            }
            team.setMembers(users);
        }

        if (teamInputDto.getProjectId() != null) {
            Project project = projectRepository.findById(teamInputDto.getProjectId())
                    .orElseThrow(() -> new ProjectNotFoundException("Project with id : "+id+" not found"));
            team.setProject(project);
        }

        teamRepository.save(team);
        return true;
    }
    @Override
    public Optional<TeamDTO> get(Long id) {
        return teamRepository.findById(id).map(teamMapper::toDto);
    }

    @Override
    public Optional<List<TeamDTO>> getAllByProjectManager(Long id) {
        return null;
    }


   @Override
   public Optional<List<TeamMemberDTO>> getMembersByTeam(Long id) {
        return teamRepository.findById(id)
                .flatMap(team -> teamMemberRepository.findTeamMembersByTeam(team)
                        .map(members -> members.stream()
                                .map(userMapper::toDtoTeamMember)
                                .toList()));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        team.ifPresent(teamRepository::delete);
        return team.isPresent();
    }
}
