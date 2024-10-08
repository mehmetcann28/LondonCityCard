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
	@NotNull
	@NotEmpty
	String password;
	@NotNull
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
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	LocalDate birthday;
}