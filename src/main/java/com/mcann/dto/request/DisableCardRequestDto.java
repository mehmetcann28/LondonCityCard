package com.mcann.dto.request;

import jakarta.validation.constraints.NotNull;

public record DisableCardRequestDto(
		@NotNull
		Long cardId
) {
}