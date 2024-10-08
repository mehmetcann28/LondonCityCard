package com.mcann.entity;

import com.mcann.utility.enums.TransitionType;
import com.mcann.utility.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_card_usage")
public class CardUsage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	Integer transferTime;
	TransitionType transitionType;
	Long lineId;
}