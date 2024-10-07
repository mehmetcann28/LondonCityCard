package com.mcann.service;

import com.mcann.entity.Line;
import com.mcann.repository.LineRepository;
import com.mcann.utility.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineService {
	private final LineRepository lineRepository;
	
	public void addLine(String lineName, String lineCode, VehicleType vehicleType, Long startStationId, Long endStationId) {
		Line line = Line.builder()
		                .lineName(lineName)
		                .lineCode(lineCode)
						.vehicleType(vehicleType)
						.endStationId(endStationId)
						.startStationId(startStationId)
						.build();
		lineRepository.save(line);
		//TODO LİNE NAME OTOMATİK DATABASE DEN GELSİN.
	}
}