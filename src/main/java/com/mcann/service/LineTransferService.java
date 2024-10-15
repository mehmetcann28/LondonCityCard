package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.LineTransfer;
import com.mcann.repository.LineTransferRepository;
import com.mcann.utility.enums.LineTransferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LineTransferService {
	
	private final LineTransferRepository lineTransferRepository;
	
	public void handleTransfer(Long firstCardUsageId, Card card, Long nextLineId) {
		LineTransferType lineTransferType = determineTransferType(firstCardUsageId);
		
		LineTransfer lineTransfer =
				LineTransfer.builder().firstCardUsageId(firstCardUsageId)  // İlk kart kullanımını kaydet
				            .nextCardUsageId(nextLineId)  // Geçilen hat bilgisi
				            .lineTransferDate(LocalDate.now())  // Şu anki zamanı `lineTransferDate` olarak ekliyoruz
				            .lineTransferTime(60)  // Önceki transfer ile şimdiki zaman arasındaki fark
				            .lineTransferType(lineTransferType)  // Geçiş türü (ilk, ikinci, üçüncü)
				            .build();
		lineTransferRepository.save(lineTransfer);
		
	}
	
	public LineTransferType determineTransferType(Long cardUsageId) {
		long transferCount = lineTransferRepository.countByFirstCardUsageIdOrNextCardUsageId(cardUsageId, cardUsageId);
		switch ((int) transferCount) {
			case 0:
				return LineTransferType.FIRST_TRANSFER;
			case 1:
				return LineTransferType.SECOND_TRANSFER;
			case 2:
				return LineTransferType.THIRD_TRANSFER;
			default:
				return LineTransferType.THIRD_TRANSFER;
		}
	}
}