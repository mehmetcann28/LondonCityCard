package com.mcann.dto.request;

import com.mcann.utility.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddLineRequestDto {
	String lineName;
	String lineCode;
	VehicleType vehicleType;
	
}