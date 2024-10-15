package com.mcann.views;

import com.mcann.utility.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwUser{
		String firstName;
		String lastName;
		String email;
		String phone;
		String address;
		String username;
		LocalDate birthday;
		CardType cardType;
}