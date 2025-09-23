package com.app.mappers;

import com.app.dto.UserRelatedFeature.TeamDTO;
import com.app.models.Team;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper {
    Team toEntity(TeamDTO teamDTO);

    @AfterMapping
    default void linkMembers(@MappingTarget Team team) {
        team.getMembers().forEach(member -> member.setTeam(team));
    }

    TeamDTO toDto(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Team partialUpdate(TeamDTO teamDTO, @MappingTarget Team team);
}