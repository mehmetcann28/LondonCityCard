package com.mcann.entity;

import com.mcann.utility.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long userId;
	String cardNumber;
	Double balance;
	LocalDate expiryDate;
	String cvv;
	@Enumerated(EnumType.STRING)
	CardType cardType;
}