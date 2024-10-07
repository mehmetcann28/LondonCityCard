package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.Transaction;
import com.mcann.repository.CardRepository;
import com.mcann.repository.TransactionRepository;
import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransactionType;
import com.mcann.utility.enums.TransitionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final CardRepository cardRepository;
	
	
	public void AddTransaction(Long cardId, Double amount, TransactionType transactionType ,PaymentType paymentType) {
		Transaction transaction = Transaction.builder()
				.cardId(cardId)
				.amount(amount)
				.transactionType(transactionType)
				.paymentType(paymentType)
				                             .build();
		transactionRepository.save(transaction);
	}
	
	public List<Transaction> GetAllTransactions() {
		return transactionRepository.findAll();
	}
	
//	public void balanceLoadCard(Long cardId, Double amount, TransactionType transactionType, PaymentType paymentType) throws Exception {
//		Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Kart bulunamadi"));
//		card.setBalance(card.getBalance() + amount);
//		transaction.setTransactionType(transactionType);
//		transaction.setPaymentType(paymentType);
//		transactionRepository.save(transaction);
//
//	}
}