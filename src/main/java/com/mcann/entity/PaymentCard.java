package com.mcann.entity;

import com.mcann.utility.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_payment_card")
public class PaymentCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String cardNumber;
	String cardHolderName;
	String expiryDate;
	String cvv;
	@Enumerated(EnumType.STRING)
	PaymentType paymentType;
}