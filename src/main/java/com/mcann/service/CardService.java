package com.mcann.service;

import com.mcann.entity.*;
import com.mcann.repository.*;
import com.mcann.utility.enums.*;
import com.mcann.views.VwCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardRepository cardRepository;
	private final TransactionRepository transactionRepository;
	private final Double standardFee = 20.0;
	private final Double firstTransferFee = standardFee - (standardFee * 0.29);
	private final Double secondTransferFee = firstTransferFee - (firstTransferFee * 0.29);
	private final Double thirdTransferFee = secondTransferFee - (secondTransferFee * 0.29);
	private final LineRepository lineRepository;
	private final CardUsageRepository cardUsageRepository;
	private final LineTransferRepository lineTransferRepository;
	
	public void addCard(String cardNumber, String cvv, CardType cardType) {
		LocalDate expiryDate = LocalDate.now().plusYears(cardType.getValidityYears());
		Card card =
				Card.builder().cardNumber(cardNumber).expiryDate(expiryDate).cvv(cvv)
				    .cardType(cardType).build();
		cardRepository.save(card);
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
		
		Transaction transaction = new Transaction();
		transaction.setCardId(cardId);
		transaction.setAmount(amount);
		transaction.setTransactionType(TransactionType.BALANCE_LOAD);
		transaction.setPaymentType(paymentType);
		transactionRepository.save(transaction);
		return updatedCard;
	}
	
	public Card cardUsageBalanceDeductionCard(Long cardId, Long lineId, PaymentType paymentType,
	                                          TransitionType transitionType)
			throws Exception {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
		Line line = lineRepository.findById(lineId).orElseThrow(() -> new Exception("Hat bilgisi bulunamadi"));
		Double fee = calculateFee(card, transitionType);
		if (card.getBalance() < fee) {
			throw new Exception("Yetersiz bakiye. Lütfen kartınıza para yükleyin.");
		}
		card.setBalance(card.getBalance() - fee);
		Card updatedCard = cardRepository.save(card);
		
		Transaction transaction =
				Transaction.builder().cardId(cardId).amount(fee).transactionType(TransactionType.BALANCE_DEDUCTION)
				           .paymentType(paymentType).build();
		transactionRepository.save(transaction);
		
		if (transitionType == TransitionType.TRANSFER) {
			// 1 saat içinde yapılan son kart kullanımını buluyoruz(INITIAL_USAGE kontrolü yapıyoruz)
			Optional<CardUsage> lastInitialUsageOpt =
					cardUsageRepository.findCardUsageByCardId(cardId, TransitionType.INITIAL_USAGE);
			
			if (lastInitialUsageOpt.isPresent()) {
				CardUsage lastInitialUsage = lastInitialUsageOpt.get();
				//Geçen süreyi hesaplama dk cinsinden
				Long minutesBetween =
						Duration.between(lastInitialUsage.getCreateAt().atStartOfDay(), LocalDate.now().atStartOfDay())
						        .toMinutes();
				if (minutesBetween < 60) {
					handleTransfer(lastInitialUsage.getId(), card, lineId);
				}
			}
		}
		
		CardUsage cardUsage = CardUsage.builder().cardId(cardId).transitionType(transitionType)
		                               .transferTime(transitionType == TransitionType.INITIAL_USAGE ? 0 : 60)
		                               .lineId(lineId).build();
		cardUsageRepository.save(cardUsage);
		
		
		return updatedCard;
	}
	
	private void handleTransfer(Long firstCardUsageId, Card card, Long nextLineId) {
		LineTransferType lineTransferType = determineTransferType(firstCardUsageId);
		
		LineTransfer lineTransfer =
				LineTransfer.builder().firstCardUsageId(firstCardUsageId)  // İlk kart kullanımını kaydet
				            .nextCardUsageId(nextLineId)  // Geçilen hat bilgisi
				            .lineTransferDate(LocalDate.now())  // Transfer tarihi
				            .lineTransferTime(60)  // Örnek süre (1 saat)
				            .lineTransferType(lineTransferType)  // Geçiş türü (ilk, ikinci, üçüncü)
				            .build();
		
		lineTransferRepository.save(lineTransfer);
	}
	
	private LineTransferType determineTransferType(Long cardUsageId) {
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
	
	private Double calculateFee(Card card, TransitionType transitionType) {
		if (transitionType == TransitionType.INITIAL_USAGE) {
			// Eğer kullanım tipi INITIAL_USAGE ise standardFee üzerinden hesapla
			return standardFee * card.getCardType().getDiscountRate();
		} else if (transitionType == TransitionType.TRANSFER) {
			// Eğer kullanım tipi TRANSFER ise transfer ücreti üzerinden hesapla
			LineTransferType lineTransferType = determineTransferType(card.getId());
			return calculateTransferFee(card, lineTransferType);
		} else {
			throw new IllegalArgumentException("Geçersiz TransitionType: " + transitionType);
		}
	}
	
	private Double calculateTransferFee(Card card, LineTransferType lineTransferType) {
		switch (lineTransferType) {
			case FIRST_TRANSFER: {
				return firstTransferFee * card.getCardType().getDiscountRate();
			}
			case SECOND_TRANSFER: {
				return secondTransferFee * card.getCardType().getDiscountRate();
			}
			case THIRD_TRANSFER: {
				return thirdTransferFee * card.getCardType().getDiscountRate();
			}
			default: {
				return thirdTransferFee * card.getCardType().getDiscountRate();
			}
		}
	}
	
	
}