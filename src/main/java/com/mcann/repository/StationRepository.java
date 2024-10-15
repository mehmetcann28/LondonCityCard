package com.mcann.repository;

import com.mcann.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
	
	boolean findByStationName(String name);
}