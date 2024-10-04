package com.mcann.entity;

import com.mcann.utility.enums.TransitionType;
import com.mcann.utility.enums.VehicleType;
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
@Table(name = "tbl_card_usage")
public class CardUsage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	Double spentPayment;
	Date spentDate;
	VehicleType vehicleType;
	Integer transferTime;
	TransitionType transitionType;
	Long stationId;
}