package com.app.mappers;

import com.app.dto.projectManagement.ProjectDTO;
import com.app.models.Project;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
    Project toEntity(ProjectDTO projectDTO);

    ProjectDTO toDto(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Project partialUpdate(ProjectDTO projectDTO, @MappingTarget Project project);
}