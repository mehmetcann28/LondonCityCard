package com.mcann.mapper;

import com.mcann.dto.request.AddLineRequestDto;
import com.mcann.entity.Line;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LineMapper {
	LineMapper INSTANCE = Mappers.getMapper(LineMapper.class);
	Line addLine(final AddLineRequestDto dto);
}