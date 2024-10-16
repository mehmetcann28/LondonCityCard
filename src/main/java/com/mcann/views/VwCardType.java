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
public class VwCardType {
	String firstName;
	String lastName;
	String cardNumber;
	Double balance;
	LocalDate expiryDate;
	CardType cardType;
}