package com.mcann.entity;

import com.mcann.utility.enums.StationType;
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
@Table(name = "tbl_station")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String stationName;
	String stationCode;
	StationType stationType;
	String location;
}