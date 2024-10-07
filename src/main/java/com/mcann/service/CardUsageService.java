package com.mcann.service;

import com.mcann.repository.CardUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardUsageService {
	private final CardUsageRepository cardUsageRepository;
}