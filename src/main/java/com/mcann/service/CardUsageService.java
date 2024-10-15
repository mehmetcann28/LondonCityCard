package com.mcann.service;

import com.mcann.entity.CardUsage;
import com.mcann.repository.CardUsageRepository;
import com.mcann.utility.enums.TransitionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUsageService {
	private final CardUsageRepository cardUsageRepository;
	
	public Optional<CardUsage> findByCardId(Long cardId) {
		return cardUsageRepository.findCardUsageByCardId(cardId,TransitionType.INITIAL_USAGE);
	}
	
	public void cardUsageBalanceDeduction(Long cardId,TransitionType transitionType,Long lineId) {
		CardUsage cardusage = CardUsage.builder()
				.cardId(cardId)
				.transitionType(transitionType)
				.transferTime(transitionType == TransitionType.INITIAL_USAGE ? 0 : 60)
				.lineId(lineId).build();
		cardUsageRepository.save(cardusage);
	}
	
	
}