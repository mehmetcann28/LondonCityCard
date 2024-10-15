package com.mcann.service;

import com.mcann.dto.request.AddStationRequestDto;
import com.mcann.entity.Line;
import com.mcann.entity.Station;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.mapper.StationMapper;
import com.mcann.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {
	
	private final StationRepository stationRepository;
	
	public void addStation(AddStationRequestDto dto) {
		
		stationRepository.save(StationMapper.INSTANCE.addStation(dto));
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
	
	public Long findByName(String name) {
		return stationRepository.findByStationName(name);
	}
	
	public List<Station> findAll() {
		return stationRepository.findAll();
	}
	
}