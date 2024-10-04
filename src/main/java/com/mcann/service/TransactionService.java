package com.mcann.service;

import com.mcann.entity.Transaction;
import com.mcann.repository.TransactionRepository;
import com.mcann.utility.enums.PaymentPoint;
import com.mcann.utility.enums.TransitionType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public void AddTransaction(Long cardId, LocalDate transactionDate, Double amount, TransitionType transitionType, PaymentPoint paymentPoint) {
		Transaction transaction = Transaction.builder()
				.cardId(cardId)
				.amount(amount)
				.transitionType(transitionType)
				.paymentPoint(paymentPoint)
				                             .build();
		transactionRepository.save(transaction);
	}
	
	public List<Transaction> GetAllTransactions() {
		return transactionRepository.findAll();
	}
}