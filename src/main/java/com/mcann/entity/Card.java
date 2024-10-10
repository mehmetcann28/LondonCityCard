package com.mcann.entity;

import com.mcann.utility.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

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
	String cardNumber;
	@Builder.Default
	Double balance = 0.0;
	LocalDate expiryDate;
	String cvv;
	@Enumerated(EnumType.STRING)
	CardType cardType;
}