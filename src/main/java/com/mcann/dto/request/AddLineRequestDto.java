package com.mcann.dto.request;

import com.mcann.utility.enums.VehicleType;
import jakarta.validation.constraints.NotNull;

public record AddLineRequestDto(
		@NotNull
		String lineCode,
		@NotNull
		VehicleType vehicleType,
		@NotNull
		String startStationName,
		@NotNull
		String endStationName
) {
}