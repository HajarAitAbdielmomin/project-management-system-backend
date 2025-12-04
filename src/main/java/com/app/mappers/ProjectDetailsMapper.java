package com.app.mappers;

import com.app.dto.projectManagement.ProjectDetailsDTO;
import com.app.models.Project;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectDetailsMapper {
    Project toEntity(ProjectDetailsDTO projectDetailsDTO);

    ProjectDetailsDTO toDto(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Project partialUpdate(ProjectDetailsDTO projectDetailsDTO, @MappingTarget Project project);
}