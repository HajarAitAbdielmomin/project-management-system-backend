package com.app.mappers;

import com.app.dto.TaskManagement.TaskDetailsDTO;
import com.app.models.Task;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskDetailsMapper {
    Task toEntity(TaskDetailsDTO taskDetailsDTO);

    TaskDetailsDTO toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskDetailsDTO taskDetailsDTO, @MappingTarget Task task);
}