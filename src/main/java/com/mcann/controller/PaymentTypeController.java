package com.mcann.controller;

import com.mcann.dto.request.AddPaymentCardRequestDto;
import com.mcann.dto.response.BaseResponse;
import com.mcann.service.PaymentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class PaymentTypeController {
	private PaymentCardService paymentCardService;
	
	public ResponseEntity<BaseResponse<Boolean>> addPaymentCard(@RequestBody AddPaymentCardRequestDto dto){
		paymentCardService.addPaymentCard(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
						.success(true)
						.data(true)
						.code(200)
						.message("Banka ve kredi kartı bilgileriniz başarıyla eklendi").build()
		);
	}
	
}