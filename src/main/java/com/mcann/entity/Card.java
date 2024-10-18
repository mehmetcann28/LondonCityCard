package com.mcann.entity;

import com.mcann.utility.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_card")
public class Card extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false, unique = true)
	String cardNumber;
	@Builder.Default
	Double balance = 0.0;
	LocalDate expiryDate;
	@Column(nullable = false, unique = true)
	String cvv;
	@Enumerated(EnumType.STRING)
	CardType cardType;
	Long paymentCardId;
}