package com.mcann.service;

import com.mcann.entity.Line;
import com.mcann.entity.Station;
import com.mcann.repository.LineRepository;
import com.mcann.repository.StationRepository;
import com.mcann.utility.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineService {
	private final LineRepository lineRepository;
	private final StationRepository stationRepository;
	
	public void addLine(Long startStationId, Long endStationId, String lineCode, VehicleType vehicleType) {
		
		String startStationName = stationRepository.findById(startStationId)
				.map(Station::getStationName)
				.orElse("Başlangıç durağı bulunamadı");
		
		String endStationName = stationRepository.findById(endStationId)
				.map(Station::getStationName)
				.orElse("Bitiş durağı bulunamadı");
		
		String lineName = startStationName + " - " + endStationName;
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