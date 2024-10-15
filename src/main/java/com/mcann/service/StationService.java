package com.mcann.service;

import com.mcann.entity.Station;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.repository.StationRepository;
import com.mcann.utility.enums.StationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	
	public String findById(Long id) {
		String stationName;
		if (stationRepository.findById(id).isPresent()) {
			stationName = stationRepository.findById(id).get().getStationName();
		}
		else {
			throw new LondonCityCardException(ErrorType.STATION_NOT_FOUND);
		}
		return stationName;
	}
	
}