package com.mcann.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
	@NotNull
	String firstName;
	@NotNull
	String lastName;
	@NotEmpty
	@Email
	String email;
	@NotNull(
			message = "Bu alanı girmek zorunludur"
	)
	@NotEmpty
	@Pattern(
			message = "Şifre en az 6 en fazla 25 karakterden oluşmalıdır ve bir büyük bir küçük ve bir özel karakter" +
			" bulunmak zorundadır.",
			regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
	)
	String password;
	@NotNull(
			message = "Bu alanı girmek zorunludur"
	)
	@NotEmpty
	String rePassword;
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Geçerli bir telefon numarası giriniz")
	String phone;
	@NotNull
	@Size(min = 10, max = 105)
	String address;
	@NotNull
	@Size(min = 3, max = 64)
	String username;
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	LocalDate birthday;
}