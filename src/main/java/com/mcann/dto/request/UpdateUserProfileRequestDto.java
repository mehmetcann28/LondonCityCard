package com.mcann.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserProfileRequestDto(
		Long id,
		@Size(min = 2, max = 50)
		String isim,
		@Size(min = 2, max = 50)
		String soyisim,
		@Email
		String email,
		@NotEmpty
		@Pattern(
				regexp = "^\\+?[0-9. ()-]{7,25}$",
				message = "Geçerli bir telefon numarası giriniz")
		String phone,
		@Size(min = 10, max = 105)
		String address,
		@Size(min = 3, max = 64)
		String username,
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
		Integer birthday,
		String customField
) {
}