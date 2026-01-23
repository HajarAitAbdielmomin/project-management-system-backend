package com.app.mappers;

import com.app.dto.projectManagement.BacklogDetailsDTo;
import com.app.models.Backlog;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BacklogDetailsMapper {
    Backlog toEntity(BacklogDetailsDTo backlogDetailsDTo);

    @AfterMapping
    default void linkTasks(@MappingTarget Backlog backlog) {
        backlog.getTasks().forEach(task -> task.setBacklog(backlog));
    }

    BacklogDetailsDTo toDto(Backlog backlog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Backlog partialUpdate(BacklogDetailsDTo backlogDetailsDTo, @MappingTarget Backlog backlog);
}