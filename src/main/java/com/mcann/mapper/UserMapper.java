package com.mcann.mapper;

import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy =
		NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	@Mapping(target = "firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	User updateUser(final UpdateUserProfileRequestDto dto);
}