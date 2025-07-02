package com.app.mappers;

import com.app.dto.SignupRequestDTO;
import com.app.models.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SignupRequestMapper {
    User toEntity(SignupRequestDTO signupRequestDTO);

    SignupRequestDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(SignupRequestDTO signupRequestDTO, @MappingTarget User user);
}