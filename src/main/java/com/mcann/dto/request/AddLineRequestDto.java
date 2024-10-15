package com.mcann.dto.request;

import com.mcann.utility.enums.VehicleType;

public record AddLineRequestDto(
		String lineName,
		String lineCode,
		VehicleType vehicleType,
		String startStationName,
		String endStationName
) {
}