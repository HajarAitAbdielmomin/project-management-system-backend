package com.app.mappers;

import com.app.dto.projectManagement.BacklogDTO;
import com.app.models.Backlog;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BacklogMapper {
    Backlog toEntity(BacklogDTO backlogDTO);

    BacklogDTO toDto(Backlog backlog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Backlog partialUpdate(BacklogDTO backlogDTO, @MappingTarget Backlog backlog);
}