package com.mcann.service;

import com.mcann.entity.Station;
import com.mcann.repository.StationRepository;
import com.mcann.utility.enums.StationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {
	
	private final StationRepository stationRepository;
	
	public void addStation(String stationName, String stationCode, StationType stationType, String location) {
		Station station = Station.builder()
		                         .stationName(stationName)
		                         .stationCode(stationCode)
		                         .stationType(stationType)
		                         .location(location)
		                         .build();
		stationRepository.save(station);
	}
	
}