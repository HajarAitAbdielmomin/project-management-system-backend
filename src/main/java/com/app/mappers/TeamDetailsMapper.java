package com.app.mappers;

import com.app.dto.teamManagement.TeamDetailsDTO;
import com.app.models.Team;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamDetailsMapper {
    Team toEntity(TeamDetailsDTO teamDetailsDTO);

    @AfterMapping
    default void linkMembers(@MappingTarget Team team) {
        team.getMembers().forEach(member -> member.setTeam(team));
    }

    TeamDetailsDTO toDto(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Team partialUpdate(TeamDetailsDTO teamDetailsDTO, @MappingTarget Team team);
}