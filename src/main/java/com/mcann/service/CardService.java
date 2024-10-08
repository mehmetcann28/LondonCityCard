package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.CardUsage;
import com.mcann.entity.Line;
import com.mcann.entity.Transaction;
import com.mcann.repository.CardRepository;
import com.mcann.repository.CardUsageRepository;
import com.mcann.repository.LineRepository;
import com.mcann.repository.TransactionRepository;
import com.mcann.utility.enums.CardType;
import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransactionType;
import com.mcann.utility.enums.TransitionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardRepository cardRepository;
	private final TransactionRepository transactionRepository;
	private final Double standardFee = 20.0;
	private final LineRepository lineRepository;
	private final CardUsageRepository cardUsageRepository;
	
	public void addCard(Long userId, String cardNumber, Double balance, String cvv,
	                    CardType cardType) {
		LocalDate expiryDate = LocalDate.now().plusYears(cardType.getValidityYears());
		Card card =
				Card.builder()
				    .userId(userId)
				    .cardNumber(cardNumber)
				    .balance(balance)
				    .expiryDate(expiryDate)
				    .cvv(cvv)
				    .cardType(cardType)
				    .build();
		cardRepository.save(card);
	}
	
	public List<Card> getAllCards() {
		return cardRepository.findAll();
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
	
	public Card firstUsageBalanceDeductionCard(Long cardId, Long lineId ,PaymentType paymentType)
			throws Exception {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
		Line line = lineRepository.findById(lineId).orElseThrow(() -> new Exception("Hat bilgisi bulunamadi"));
		Double discountedFee = card.getCardType().getDiscountRate() * standardFee;
		if (card.getBalance()<discountedFee) {
			throw new Exception("Yetersiz bakiye. Lütfen kartınıza para yükleyin.");
		}
		card.setBalance(card.getBalance() - discountedFee);
		Card updatedCard = cardRepository.save(card);
		
		Transaction transaction = Transaction.builder()
				.cardId(cardId)
				.amount(discountedFee)
				.transactionType(TransactionType.BALANCE_DEDUCTION)
				.paymentType(paymentType)
				.build();
		transactionRepository.save(transaction);
		
		CardUsage cardUsage = CardUsage.builder()
				.cardId(cardId)
				.transitionType(TransitionType.INITIAL_USAGE)
				.transferTime(0)
				.lineId(lineId)
				.build();
		cardUsageRepository.save(cardUsage);
		return updatedCard;
	}
	
	
	
}