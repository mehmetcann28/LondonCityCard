package com.mcann.controller;

import com.mcann.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
}