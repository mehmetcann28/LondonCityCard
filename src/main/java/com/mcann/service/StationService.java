package com.mcann.service;

import com.mcann.repository.StationRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService {
	
	private final StationRepository stationRepository;
	
	public StationService(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}
}