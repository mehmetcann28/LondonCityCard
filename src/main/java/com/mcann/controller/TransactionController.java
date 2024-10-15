package com.mcann.controller;

import static com.mcann.constant.RestApis.*;
import com.mcann.constant.RestApis;
import com.mcann.dto.response.BaseResponse;
import com.mcann.service.TransactionService;
import com.mcann.views.VwTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
	@GetMapping(GETALLTRANSACTION)
	public ResponseEntity<BaseResponse<List<VwTransaction>>> getAllTransactions() {
		return ResponseEntity.ok(BaseResponse.<List<VwTransaction>>builder()
				                         .success(true)
				                         .message("İşlem başarılı bir şekilde çalıştı")
				                         .data(transactionService.getAllVwTransactions())
				                         .code(200).build());
	}
	
}