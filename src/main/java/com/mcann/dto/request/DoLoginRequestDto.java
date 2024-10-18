package com.mcann.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DoLoginRequestDto(
		@NotNull
		@Size(min = 3, max = 40)
		String username,
		@NotEmpty(
				message = "Bu alanı girmek zorunludur"
		)
		@Pattern(
				message = "Şifre en az 6 en fazla 25 karakterden oluşmalıdır ve bir büyük bir küçük ve bir özel " +
						"karakter" + " bulunmak zorundadır.",
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
		String password
) {
}