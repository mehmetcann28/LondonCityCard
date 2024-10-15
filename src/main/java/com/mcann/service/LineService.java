package com.mcann.service;

import com.mcann.dto.request.AddLineRequestDto;
import com.mcann.entity.Line;
import com.mcann.mapper.LineMapper;
import com.mcann.repository.LineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineService {
	private final LineRepository lineRepository;
	private final StationService stationService;
	
	
	public void addLine(AddLineRequestDto dto) {
		
//		String startStationName = stationService.findById(startStationId);
//		String endStationName = stationService.findById(endStationId);
//		String lineName = startStationName + " - " + endStationName;
		
		Long startStationId = stationService.findByName(dto.startStationName());
		Long endStationId = stationService.findByName(dto.endStationName());
		
		String startStationName = dto.startStationName();
		String endStationName = dto.endStationName();
		Line saveLine = lineRepository.save(LineMapper.INSTANCE.addLine(dto));
		saveLine.setStartStationId(startStationId);
		saveLine.setEndStationId(endStationId);
		
		String lineName = startStationName + " - " + endStationName;
		saveLine.setLineName(lineName);
		lineRepository.save(saveLine);
	}
	
	public List<Line> getAllLines() {
		return lineRepository.findAll();
	}
	
	
}