package com.app.repository;

import com.app.models.Team;
import com.app.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{
    Optional<List<TeamMember>> findTeamMembersByTeam(Team team);
}
