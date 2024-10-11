package com.mcann.service;

import com.mcann.entity.*;
import com.mcann.repository.*;
import com.mcann.utility.enums.*;
import com.mcann.views.VwCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardRepository cardRepository;
	private final Double standardFee = 20.0;
	private final Double firstTransferFee = standardFee - (standardFee * 0.29);
	private final Double secondTransferFee = firstTransferFee - (firstTransferFee * 0.29);
	private final Double thirdTransferFee = secondTransferFee - (secondTransferFee * 0.29);
	private final TransactionService transactionService;
	private final CardUsageService cardUsageService;
	private final LineTransferService lineTransferService;
	
	public Card addUserCard(CardType cardType) {
		String cardNumber = convertToNumeric(UUID.randomUUID().toString().substring(0,16));
		String cvv = convertToNumeric(UUID.randomUUID().toString().substring(0,3));
		Card card =
				Card.builder()
						.cardNumber(cardNumber)
						.cvv(cvv)
						.expiryDate(LocalDate.now().plusYears(cardType.getValidityYears()))
						.cardType(cardType)
				    .build();
		return cardRepository.save(card);
	}
	
	public Card addCard(CardType cardType) {
		if (cardType != CardType.STANDARD) {
			throw new IllegalArgumentException("Bu kart tipi için kullanıcı kaydı gereklidir.");
		}
		String cardNumber = convertToNumeric(UUID.randomUUID().toString().substring(0,16));
		String cvv = convertToNumeric(UUID.randomUUID().toString().substring(0,3));
		Card card =
				Card.builder()
				    .cardNumber(cardNumber)
				    .cvv(cvv)
				    .expiryDate(LocalDate.now().plusYears(cardType.getValidityYears()))
				    .cardType(cardType)
				    .build();
		return cardRepository.save(card);
	}
	
	private static String convertToNumeric(String input) {
		StringBuilder numericString = new StringBuilder();
		for (char c : input.toCharArray()) {
			numericString.append((int) c % 10); // ASCII değerlerini sayısal karakterlere dönüştürme}
		}
		return numericString.toString();
	}
	
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}
	
	public List<VwCard> getAllVwCards() {
		return cardRepository.getAllCard();
	}
	
	public Card getCardById(Long id) {
		return cardRepository.findById(id).orElse(null);
	}
	
	public Card balanceLoadCard(Long cardId, Double amount, PaymentType paymentType) throws Exception {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
		card.setBalance(card.getBalance() + amount);
		Card updatedCard = cardRepository.save(card);
		
		transactionService.balanceLoadCard(cardId, amount, paymentType);
		
		return updatedCard;
	}
	
	public Card cardUsageBalanceDeductionCard(Long cardId, Long lineId, PaymentType paymentType,
	                                          TransitionType transitionType)
			throws Exception {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
		Double fee = calculateFee(card, transitionType);
		System.out.println("Calculated fee for transitionType: " + transitionType + " is " + fee);
		if (card.getBalance() < fee) {
			throw new Exception("Yetersiz bakiye. Lütfen kartınıza para yükleyin.");
		}
		card.setBalance(card.getBalance() - fee);
		Card updatedCard = cardRepository.save(card);
		
		transactionService.balanceDeductionCard(cardId,fee,paymentType);
		
		if (transitionType == TransitionType.TRANSFER) {
			// 1 saat içinde yapılan son kart kullanımını buluyoruz(INITIAL_USAGE kontrolü yapıyoruz)
			Optional<CardUsage> lastInitialUsageOpt = cardUsageService.findByCardId(cardId);
			
			if (lastInitialUsageOpt.isPresent()) {
				CardUsage lastInitialUsage = lastInitialUsageOpt.get();
				//Geçen süreyi hesaplama dk cinsinden
				long currentMillis = System.currentTimeMillis();
				long lastUsageMillis = lastInitialUsage.getCreateAt().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
				long millisecondsBetween = currentMillis - lastUsageMillis;
				if (millisecondsBetween < 3600000) {
					lineTransferService.handleTransfer(lastInitialUsage.getId(), card, lineId);
				}
			}
		}
		
		cardUsageService.cardUsageBalanceDeduction(cardId,transitionType,lineId);
		
		return updatedCard;
	}
	
	private Double calculateFee(Card card, TransitionType transitionType) {
		if (transitionType == TransitionType.INITIAL_USAGE) {
			// Eğer kullanım tipi INITIAL_USAGE ise standardFee üzerinden hesapla
			return standardFee * card.getCardType().getDiscountRate();
		} else if (transitionType == TransitionType.TRANSFER) {
			// Eğer kullanım tipi TRANSFER ise transfer ücreti üzerinden hesapla
			LineTransferType lineTransferType = lineTransferService.determineTransferType(card.getId());
			return calculateTransferFee(card, lineTransferType);
		} else {
			throw new IllegalArgumentException("Geçersiz TransitionType: " + transitionType);
		}
	}
	
	private Double calculateTransferFee(Card card, LineTransferType lineTransferType) {
		switch (lineTransferType) {
			case FIRST_TRANSFER:
				return firstTransferFee;
			case SECOND_TRANSFER:
				return secondTransferFee;
			case THIRD_TRANSFER:
				return thirdTransferFee;
			default:
				return thirdTransferFee;
		}
	}
	
}