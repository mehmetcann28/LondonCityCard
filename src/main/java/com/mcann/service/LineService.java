package com.mcann.service;

import com.mcann.dto.request.AddLineRequestDto;
import com.mcann.entity.Line;
import com.mcann.entity.Station;
import com.mcann.mapper.LineMapper;
import com.mcann.repository.LineRepository;
import com.mcann.repository.StationRepository;
import com.mcann.utility.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineService {
	private final LineRepository lineRepository;
	private final StationService stationService;
	
	public void addLine(AddLineRequestDto dto) {
		
		String startStationName = stationService.findById(startStationId);
		
		String endStationName = stationService.findById(endStationId);
		
		String lineName = startStationName + " - " + endStationName;
		lineRepository.save(LineMapper.INSTANCE.addLine(dto));
	}
}