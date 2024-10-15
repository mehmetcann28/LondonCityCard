package com.mcann.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserProfileRequestDto {
//	@NotNull
	Long id;
//	@NotNull
//	@Size(min = 2, max = 50)
	String isim;
//	@NotNull
//	@Size(min = 2, max = 50)
	String soyisim;
//	@Email
	String email;
//	@NotEmpty
//	@Size(min = 8, max = 64)
//	@Pattern(
//			message = "Şifreniz en az 8 en fazla 64 karakter olmalı, Şirenizde En az Bir büyük bir küçük harf ve özel karakter olmalıdır.",
//			regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
//	)
	String phone;
	String address;
//	@NotNull
	String username;
}