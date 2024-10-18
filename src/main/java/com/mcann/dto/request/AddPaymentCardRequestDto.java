package com.mcann.dto.request;

import com.mcann.utility.enums.PaymentType;
import jakarta.validation.constraints.NotNull;

public record AddPaymentCardRequestDto(
		@NotNull
		String cardNumber,
		@NotNull
		String cardHolderName,
		@NotNull
		String expiryDate,
		@NotNull
		String cvv,
		@NotNull
		PaymentType paymentType
) {
}