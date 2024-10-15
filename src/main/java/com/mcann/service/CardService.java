package com.mcann.service;
import static com.mcann.utility.Constant.*;
import com.mcann.entity.*;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.repository.*;
import com.mcann.utility.Constant;
import com.mcann.utility.enums.*;
import com.mcann.views.VwCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardRepository cardRepository;
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
			throw new LondonCityCardException(ErrorType.INVALIDCARDTYPE_EXCEPTION);
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
	
	public Card balanceLoadCard(Long cardId, Double amount, PaymentType paymentType) {
		Optional<Card> card = cardRepository.findById(cardId);
		if (!card.isPresent()) {
			throw new LondonCityCardException(ErrorType.CARD_NOT_FOUND);
		}
		Card cardValue = card.get();
		cardValue.setBalance(cardValue.getBalance() + amount);
		Card updatedCard = cardRepository.save(cardValue);
		
		transactionService.balanceLoadCard(cardId, amount, paymentType);
		
		return updatedCard;
	}
	
	public Card cardUsageBalanceDeductionCard(Long cardId, Long lineId, PaymentType paymentType,
	                                          TransitionType transitionType) {
		Optional<Card> card = cardRepository.findById(cardId);
		if (!card.isPresent()) {
			throw new LondonCityCardException(ErrorType.CARD_NOT_FOUND);
		}
		Card cardValue = card.get();
		Double fee = calculateFee(cardValue, transitionType);
		if (cardValue.getBalance() < fee) {
			throw new LondonCityCardException(ErrorType.YETERSIZ_BAKIYE_HATASI);
		}
		cardValue.setBalance(cardValue.getBalance() - fee);
		Card updatedCard = cardRepository.save(cardValue);
		transactionService.balanceDeductionCard(cardId,fee,paymentType);
		
		if (transitionType == TransitionType.TRANSFER) {
			// 1 saat içinde yapılan son kart kullanımını buluyoruz(INITIAL_USAGE kontrolü yapıyoruz)
			Optional<CardUsage> lastInitialUsageOpt = cardUsageService.findByCardId(cardId);
			
			if (lastInitialUsageOpt.isPresent()) {
				CardUsage lastInitialUsage = lastInitialUsageOpt.get();
				//Geçen süreyi hesaplama dk cinsinden
				Long minutesBetween =
						Duration.between(lastInitialUsage.getCreateAt().atStartOfDay(), LocalDate.now().atStartOfDay())
						        .toMinutes();
				if (minutesBetween < 60) {
					lineTransferService.handleTransfer(lastInitialUsage.getId(), cardValue, lineId);
				}
			}
		}
		
		cardUsageService.cardUsageBalanceDeduction(cardId,transitionType,lineId);
		
		return updatedCard;
	}
	
	private Double calculateFee(Card card, TransitionType transitionType) {
		if (transitionType == TransitionType.INITIAL_USAGE) {
			// Eğer kullanım tipi INITIAL_USAGE ise standardFee üzerinden hesapla
			return STANDARTFEE * card.getCardType().getDiscountRate();
		} else if (transitionType == TransitionType.TRANSFER) {
			// Eğer kullanım tipi TRANSFER ise transfer ücreti üzerinden hesapla
			LineTransferType lineTransferType = lineTransferService.determineTransferType(card.getId());
			return calculateTransferFee(card, lineTransferType);
		} else {
			throw new LondonCityCardException(ErrorType.TRANSITION_NOT_FOUND);
		}
	}
	
	private Double calculateTransferFee(Card card, LineTransferType lineTransferType) {
		switch (lineTransferType) {
			case FIRST_TRANSFER:
				return FIRSTTRANSFERFEE;
			case SECOND_TRANSFER:
				return SECONDTRANSFERFEE;
			case THIRD_TRANSFER:
				return THIRDTRANSFERFEE;
			default:
				return THIRDTRANSFERFEE;
		}
	}
	
}