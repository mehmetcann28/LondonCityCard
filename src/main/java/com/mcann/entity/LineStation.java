package com.mcann.entity;

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
@Table(name = "tbl_line_station")
public class LineStation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long lineId;
	Long stationId;
}