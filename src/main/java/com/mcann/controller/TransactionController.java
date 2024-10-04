package com.mcann.controller;

import com.mcann.service.TransactionService;
import com.mcann.utility.enums.PaymentPoint;
import com.mcann.utility.enums.TransitionType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	private final TransactionService transactionService;
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	// localhost:9090/transaction/add-transaction
	@GetMapping("/add-transaction")
	public String addTransaction(){
		transactionService.AddTransaction(1L, LocalDate.now(), 15.0, TransitionType.RECHARGE, PaymentPoint.CENTRAL_OFFICE);
		transactionService.AddTransaction(3L, LocalDate.now(), 20.0, TransitionType.RECHARGE, PaymentPoint.ONLINE);
		return "Transaction added";
	}
	
	
}