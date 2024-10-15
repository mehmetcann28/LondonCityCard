package com.mcann.repository;

import com.mcann.entity.Line;
import com.mcann.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
	@Query("select s.id from Station s where s.stationName = ?1")
	Long findByStationName(String stationName);
}