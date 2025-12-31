package com.app.mappers;

import com.app.dto.teamManagement.TeamDTO;
import com.app.models.Team;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper {
    Team toEntity(TeamDTO teamDTO);

    TeamDTO toDto(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Team partialUpdate(TeamDTO teamDTO, @MappingTarget Team team);
}