package com.mcann.mapper;

import com.mcann.dto.request.AddStationRequestDto;
import com.mcann.entity.Station;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StationMapper {
	StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);
	Station addStation(final AddStationRequestDto dto);
}