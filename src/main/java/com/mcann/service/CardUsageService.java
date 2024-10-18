package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.CardUsage;
import com.mcann.entity.PaymentCard;
import com.mcann.repository.CardUsageRepository;
import com.mcann.utility.enums.TransitionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUsageService {
	private final CardUsageRepository cardUsageRepository;
	
	// Belirli bir transitionType ile kart kullanımını bulma
	public Optional<CardUsage> findByCardId(Long cardId) {
		return cardUsageRepository.findCardUsageByCardId(cardId, TransitionType.INITIAL_USAGE);
	}
	
	// Kartın en son kullanımını bulma
	public Optional<CardUsage> findLastUsageByCardId(Long cardId) {
		System.out.println("Şuan buradayız");
		return cardUsageRepository.findLastCardUsageByCardId(cardId);
	}
	
	// Kartın son kullanımına göre transition type belirler ve zaman farkını döndürür.
	public TransitionType determineTransitionType(Optional<CardUsage> lastUsageOpt, LocalDateTime now) {
		return lastUsageOpt.map(lastUsage -> {
			long minutesBetween = Duration.between(lastUsage.getCreateAt(), now).toMinutes();
			return minutesBetween < 60 ? TransitionType.TRANSFER : TransitionType.INITIAL_USAGE;
		}).orElse(TransitionType.INITIAL_USAGE);
	}
	
	// İki kart kullanımı arasındaki zaman farkını hesaplar
	public long calculateTimeDifference(CardUsage firstUsage, LocalDateTime now) {
		return Duration.between(firstUsage.getCreateAt(), now).toMinutes();
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