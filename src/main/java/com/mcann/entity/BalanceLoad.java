package com.mcann.entity;

import com.mcann.utility.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_balance_load")
public class BalanceLoad extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	@Builder.Default
	Double lastLoadQuantity = 50d;
	PaymentType paymentPoint;
	
}