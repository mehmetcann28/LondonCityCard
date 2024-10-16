package com.mcann.dto.request;

import com.mcann.utility.enums.PaymentType;
import jakarta.validation.constraints.NotNull;

public record BalanceLoadCardRequestDto(
	@NotNull
	Long cardId,
	@NotNull
	Double amount,
	@NotNull
	PaymentType paymentType
) {
}