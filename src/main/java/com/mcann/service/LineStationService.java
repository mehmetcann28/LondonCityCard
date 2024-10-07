package com.mcann.service;

import com.mcann.repository.LineStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineStationService {
	private final LineStationRepository lineStationRepository;
}