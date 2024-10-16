package com.mcann.dto.request;

import com.mcann.utility.enums.CardType;
import jakarta.validation.constraints.NotNull;

public record AddCardRequestDto(
		@NotNull
		CardType cardType
) {
}