package com.mcann.entity;

import com.mcann.utility.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_line")
public class Line extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String lineName;
	String lineCode;
	@Enumerated(EnumType.STRING)
	VehicleType vehicleType;
	Long startStationId;
	Long endStationId;
}