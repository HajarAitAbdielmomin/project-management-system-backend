package com.app.mappers;

import com.app.dto.LoginRequestDTO;
import com.app.models.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginRequestMapper {
    User toEntity(LoginRequestDTO loginRequestDTO);

    LoginRequestDTO toDto(LoginRequestDTO loginRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoginRequestDTO partialUpdate(LoginRequestDTO loginRequestDTODto, @MappingTarget LoginRequestDTO loginRequestDTO);
}