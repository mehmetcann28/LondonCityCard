package com.mcann.repository;

import com.mcann.entity.Transaction;
import com.mcann.views.VwTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("select new com.mcann.views.VwTransaction(u.firstName,u.lastName,t.amount,t.paymentType,t.transactionType,t.createAt) from Transaction t join User u on t.cardId = u.id")
	List<VwTransaction> getAllTransactions();
}