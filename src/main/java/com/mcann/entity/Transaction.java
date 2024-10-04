package com.mcann.entity;

import com.mcann.utility.enums.PaymentPoint;
import com.mcann.utility.enums.TransitionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	Date transactionDate;
	Double amount;
	TransitionType transitionType;
	PaymentPoint paymentPoint;
	//TODO PARA YÜKLEME EKLENECEK
}