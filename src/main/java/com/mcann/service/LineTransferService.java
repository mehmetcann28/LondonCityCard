package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.CardUsage;
import com.mcann.entity.LineTransfer;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.repository.LineTransferRepository;
import com.mcann.utility.enums.LineTransferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineTransferService {
	
	private final LineTransferRepository lineTransferRepository;
	private final CardUsageService cardUsageService;
	
	public void handleTransfer(Long firstCardUsageId, Card card, Long nextLineId) {
		LineTransferType lineTransferType = determineTransferType(firstCardUsageId);
		System.out.println("Card id si: "+card.getId());
		System.out.println("firstCardUsageId si: "+firstCardUsageId);
		Optional<CardUsage> firstCardUsageOpt = cardUsageService.findLastUsageByCardId(card.getId());
		if (!firstCardUsageOpt.isPresent()) {
			throw new LondonCityCardException(ErrorType.CARD_USAGE_NOT_FOUND);
		}
		
		System.out.println("merhaba");
		CardUsage firstCardUsage = firstCardUsageOpt.get();
		
		LocalDateTime now = LocalDateTime.now();
		
		long minutesBetween = Duration.between(firstCardUsage.getCreateAt(), now).toMinutes();
		LineTransfer lineTransfer =
				LineTransfer.builder().firstCardUsageId(firstCardUsageId)  // İlk kart kullanımını kaydet
				            .nextCardUsageId(nextLineId)  // Geçilen hat bilgisi
				            .lineTransferDate(now.toLocalDate())  // Şu anki zamanı `lineTransferDate` olarak ekliyoruz
				            .lineTransferTime((int) minutesBetween)  // Önceki transfer ile şimdiki zaman arasındaki fark
				            .lineTransferType(lineTransferType)  // Geçiş türü (ilk, ikinci, üçüncü)
				            .build();
		lineTransferRepository.save(lineTransfer);
		
	}
	
	public LineTransferType determineTransferType(Long cardUsageId) {
		long transferCount = lineTransferRepository.countByFirstCardUsageIdOrNextCardUsageId(cardUsageId, cardUsageId);
		System.out.println("Şuan buradayız");
		return switch ((int) transferCount) {
			case 0 -> LineTransferType.FIRST_TRANSFER;
			case 1 -> LineTransferType.SECOND_TRANSFER;
			default -> LineTransferType.THIRD_TRANSFER;
		};
	}
}