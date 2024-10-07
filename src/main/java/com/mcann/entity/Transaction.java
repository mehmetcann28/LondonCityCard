package com.mcann.entity;

import com.mcann.utility.enums.PaymentType;
import com.mcann.utility.enums.TransactionType;
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
@Table(name = "tbl_transaction")
public class Transaction extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	Double amount;
	@Enumerated(EnumType.STRING)
	TransactionType transactionType;
	@Enumerated(EnumType.STRING)
	PaymentType paymentType;
	//TODO PARA YÜKLEME EKLENECEK
}