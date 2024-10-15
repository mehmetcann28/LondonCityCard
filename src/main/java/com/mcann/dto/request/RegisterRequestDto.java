package com.mcann.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterRequestDto(
		@NotNull
		String firstName,
		@NotNull
		String lastName,
		@NotEmpty @Email
		String email,
		@NotEmpty(
				message = "Bu alanı girmek zorunludur"
		)
		@Pattern(
				message = "Şifre en az 6 en fazla 25 karakterden oluşmalıdır ve bir büyük bir küçük ve bir özel " +
		                          "karakter" + " bulunmak zorundadır.",
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
		String password,
		@NotEmpty(
				message = "Bu alanı girmek zorunludur")
		String rePassword,
		@NotNull
		@NotEmpty
		@Pattern(
				regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Geçerli bir telefon numarası giriniz")
		String phone,
		@NotNull
		@Size(min = 10, max = 105)
		String address,
		@NotNull
		@Size(min = 3, max = 64)
		String username,
		@NotNull
		LocalDate birthday
) {
}