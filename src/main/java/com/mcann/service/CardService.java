package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.Transaction;
import com.mcann.repository.CardRepository;
import com.mcann.repository.TransactionRepository;
import com.mcann.utility.enums.CardType;
import com.mcann.utility.enums.PaymentPoint;
import com.mcann.utility.enums.TransitionType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardService {
	private final CardRepository cardRepository;
	private final TransactionRepository transactionRepository;
	
	public CardService(CardRepository cardRepository, TransactionRepository transactionRepository) {
		this.cardRepository = cardRepository;
		this.transactionRepository = transactionRepository;
	}
	
	public void addCard(Long userId,String cardNumber, Double balance, LocalDate expiryDate, String cvv, CardType cardType) {
		Card card =
				Card.builder()
				    .userId(userId)
				    .cardNumber(cardNumber)
				    .balance(balance)
				    .expiryDate(expiryDate).cvv(cvv).cardType(cardType).build();
		cardRepository.save(card);
	}
	
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}
	
	public Card getCardById(Long id) {
		return cardRepository.findById(id).orElse(null);
	}
	
	/*public Card rechargeCard(Long cardId, Double amount, PaymentPoint paymentPoint) throws Exception {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
		card.setBalance(card.getBalance() + amount);
		Card updatedCard = cardRepository.save(card);
		
		Transaction transaction = new Transaction();
		transaction.setCardId(cardId);
		transaction.setAmount(amount);
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransitionType(TransitionType.RECHARGE);
		transaction.setPaymentPoint(paymentPoint);
		transactionRepository.save(transaction);
		return updatedCard;
	}*/
}