package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.entity.Transaction;
import com.mcann.repository.CardRepository;
import com.mcann.repository.TransactionRepository;
import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransactionType;
import com.mcann.utility.enums.TransitionType;
import com.mcann.views.VwTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public void balanceLoadCard (Long cardId, Double amount, PaymentType paymentType) {
		Transaction transaction = Transaction.builder()
				.cardId(cardId)
				.amount(amount)
				.transactionType(TransactionType.BALANCE_LOAD)
				.paymentType(paymentType).build();
		transactionRepository.save(transaction);
	}
	
	public void balanceDeductionCard (Long cardId, Double amount, PaymentType paymentType) {
		Transaction transaction = Transaction.builder()
		                                     .cardId(cardId)
		                                     .amount(amount)
		                                     .transactionType(TransactionType.BALANCE_DEDUCTION)
		                                     .paymentType(paymentType).build();
		transactionRepository.save(transaction);
	}
	
	public List<Transaction> GetAllTransactions() {
		return transactionRepository.findAll();
	}
	
	public List<VwTransaction> getAllVwTransactions() {
		return transactionRepository.getAllTransactions();
	}
	
}