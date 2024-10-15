package com.mcann.views;

import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwTransaction {
	String firstName;
	String lastName;
	Double amount;
	PaymentType paymentType;
	TransactionType transactionType;
	LocalDate transactionDate;
}