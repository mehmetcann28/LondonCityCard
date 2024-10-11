package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.LineTransfer;
import com.mcann.repository.LineTransferRepository;
import com.mcann.utility.enums.LineTransferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineTransferService {
	
	private final LineTransferRepository lineTransferRepository;
	
	public void handleTransfer(Long firstCardUsageId, Card card, Long nextLineId) {
		Optional<LineTransfer> existingTransferOpt = lineTransferRepository.findByFirstCardUsageIdAndNextCardUsageId(firstCardUsageId, nextLineId);
		if (existingTransferOpt.isPresent()) {
			// Zaten bir transfer kaydı varsa güncelleme yap ve log ekle
			LineTransfer existingTransfer = existingTransferOpt.get();
			System.out.println("Bu transfer zaten var, güncelleme yapılmadı. ID: " + existingTransfer.getId());
		} else {
			// Şu anki zamanı milisaniye cinsinden alıyoruz
			long currentMillis = System.currentTimeMillis();
			LineTransferType lineTransferType = determineTransferType(firstCardUsageId);
			
			LineTransfer lineTransfer =
					LineTransfer.builder().firstCardUsageId(firstCardUsageId)  // İlk kart kullanımını kaydet
					            .nextCardUsageId(nextLineId)  // Geçilen hat bilgisi
					            .lineTransferDate(currentMillis)  // Şu anki zamanı `lineTransferDate` olarak ekliyoruz
					            .lineTransferTime(currentMillis - firstCardUsageId)  // İlk kullanım ile şimdiki zaman arasındaki fark
					            .lineTransferType(lineTransferType)  // Geçiş türü (ilk, ikinci, üçüncü)
					            .build();
			
			lineTransferRepository.save(lineTransfer);
			System.out.println("Yeni transfer kaydı eklendi. ID: " + lineTransfer.getId());
		}
	}
	
	public LineTransferType determineTransferType(Long cardUsageId) {
		// Şu anki zamanı milisaniye olarak alıyoruz
		long currentMillis = System.currentTimeMillis();
		
		// Önceki transfer kayıtlarını zaman sırasına göre alıyoruz.
		List<LineTransfer>
				lastTransfers = lineTransferRepository.findByFirstCardUsageIdOrNextCardUsageIdOrderByLineTransferDateDesc(cardUsageId, cardUsageId);
		
		if (!lastTransfers.isEmpty()) {
			LineTransfer lastTransfer = lastTransfers.get(0);  // En son transfer kaydını alıyoruz.
			long timeDifference = currentMillis - lastTransfer.getLineTransferDate();
			
			// Fark milisaniye cinsinden 1 saatten (3600000 ms) az ise transfer sayısını artır
			if (timeDifference < 3600000) {  // 1 saat içinde yapılan transfer
				if (lastTransfer.getLineTransferType() == LineTransferType.FIRST_TRANSFER) {
					return LineTransferType.SECOND_TRANSFER;
				} else if (lastTransfer.getLineTransferType() == LineTransferType.SECOND_TRANSFER) {
					return LineTransferType.THIRD_TRANSFER;
				}
			}
		}
		return LineTransferType.FIRST_TRANSFER;
	}
}