package com.mcann.service;
import static com.mcann.utility.Constant.*;

import com.mcann.dto.request.AddCardRequestDto;
import com.mcann.dto.request.BalanceLoadCardRequestDto;
import com.mcann.dto.request.DisableCardRequestDto;
import com.mcann.entity.*;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.mapper.CardMapper;
import com.mcann.repository.*;
import com.mcann.utility.enums.*;
import com.mcann.views.VwCardNotUser;
import com.mcann.views.VwCardType;
import com.mcann.views.VwCardUser;
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
//		String cardNumber = convertToNumeric(UUID.randomUUID().toString().substring(0,16));
//		String cvv = convertToNumeric(UUID.randomUUID().toString().substring(0,3));
//		LocalDate expiryDate = LocalDate.now().plusYears(cardType.getValidityYears());
//		card.setCardNumber(cardNumber);
//		card.setCvv(cvv);
//		card.setExpiryDate(expiryDate);
//		card.setCardType(cardType);
		return cardRepository.save(CardMapper.INSTANCE.addUserCard(cardType));
	}
	
	public void addCard(AddCardRequestDto dto) {
		if (dto.cardType() != CardType.STANDARD) {
			throw new LondonCityCardException(ErrorType.INVALIDCARDTYPE_EXCEPTION);
		}
//		String cardNumber = convertToNumeric(UUID.randomUUID().toString().substring(0,16));
//		String cvv = convertToNumeric(UUID.randomUUID().toString().substring(0,3));
//		LocalDate expiryDate = LocalDate.now().plusYears(dto.cardType().getValidityYears());
//		card.setCardNumber(cardNumber);
//		card.setCvv(cvv);
//		card.setExpiryDate(expiryDate);
		cardRepository.save(CardMapper.INSTANCE.addCard(dto));
	}
	
	
	
	public void deleteCardById(Long cardId) {
		cardRepository.findById(cardId)
		                          .orElseThrow(() -> new LondonCityCardException(ErrorType.CARD_NOT_FOUND));
		cardRepository.deleteById(cardId);
	}
	
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}
	
	public List<VwCardUser> getUserVwCards() {
		return cardRepository.findUserCards();
	}
	
	public List<VwCardNotUser> getNotUserVwCards() {
		return cardRepository.findNotUserCards();
	}
	
	public List<VwCardType> getStandardVwCards() {
		return cardRepository.findStandardCards();
	}
	
	public List<VwCardType> getStudentVwCards() {
		return cardRepository.findStudentCards();
	}
	
	public List<VwCardType> getTeacherVwCards() {
		return cardRepository.findTeacherCards();
	}
	
	public List<VwCardType> getElderlyVwCards() {
		return cardRepository.findElderlyCards();
	}
	
	public List<VwCardType> getDiscountedVwCards() {
		return cardRepository.findDiscountedCards();
	}
	
	public List<Card> getActiveCards() {
		return cardRepository.findActiveCards();
	}
	
	public List<Card> getPassiveCards() {
		return cardRepository.findPassiveCards();
	}
	
	public Card getCardById(Long id) {
		return cardRepository.findById(id).orElseThrow(() -> new LondonCityCardException(ErrorType.CARD_NOT_FOUND));
	}
	
	public void setDisabledCard(DisableCardRequestDto dto) {
		Card card = cardRepository.findById(dto.cardId())
		                          .orElseThrow(() -> new LondonCityCardException(ErrorType.CARD_NOT_FOUND));
		card.setState(State.PASSIVE);
		card.setUpdateAt(LocalDate.now());
		cardRepository.save(card);
	}
	
	public Card balanceLoadCard(BalanceLoadCardRequestDto dto) {
		Optional<Card> cardOpt = cardRepository.findById(dto.cardId());
		if (cardOpt.isEmpty()) {
			throw new LondonCityCardException(ErrorType.CARD_NOT_FOUND);
		}
		Card card = cardOpt.get();
		
		Card updatedCard = CardMapper.INSTANCE.updateBalanceFromDto(dto, card);
		
		cardRepository.save(updatedCard);
		transactionService.balanceLoadCard(dto.cardId(), dto.amount(), dto.paymentType());
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