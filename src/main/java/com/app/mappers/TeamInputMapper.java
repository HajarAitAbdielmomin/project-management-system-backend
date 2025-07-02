package com.app.mappers;

import com.app.dto.UserRelatedFeature.TeamInputDto;
import com.app.models.Team;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TeamInputMapper {
    Team toEntity(TeamInputDto teamInputDto);

    TeamInputDto toDto(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Team partialUpdate(TeamInputDto teamInputDto, @MappingTarget Team team);
}