package com.mcann.mapper;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	User registerUser(final RegisterRequestDto dto);
	User updateUser(final UpdateUserProfileRequestDto dto, @MappingTarget User user);
}