package com.mcann.dto.request;

import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransitionType;
import jakarta.validation.constraints.NotNull;

public record CardUsageBalaceDeductionRequestDto(
		@NotNull
		Long cardId,
		@NotNull
		Long lineId,
		@NotNull
		PaymentType paymentType,
		@NotNull
		TransitionType transitionType
) {
}