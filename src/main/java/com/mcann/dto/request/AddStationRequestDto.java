package com.mcann.dto.request;

import com.mcann.utility.enums.StationType;
import jakarta.validation.constraints.NotNull;
@NotNull
public record AddStationRequestDto(
		String stationName,
		String stationCode,
		StationType stationType,
		String location
) {
}