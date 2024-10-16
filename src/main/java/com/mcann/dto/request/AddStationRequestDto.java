package com.mcann.dto.request;

import com.mcann.utility.enums.StationType;
import jakarta.validation.constraints.NotNull;
public record AddStationRequestDto(
		@NotNull
		String stationName,
		@NotNull
		String stationCode,
		@NotNull
		StationType stationType,
		@NotNull
		String location
) {
}