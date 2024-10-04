package com.mcann.entity;

import com.mcann.utility.enums.VehicleType;
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
@Table(name = "tbl_line")
public class Line {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String lineName;
	String lineCode;
	VehicleType vehicleType;
	Long startStationId;
	Long endStationId;
}