package com.mcann.entity;

import com.mcann.utility.enums.LineTransferType;
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
@Table(name = "tbl_line_transfer")
public class LineTransfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long firstCardUsageId;
	Long nextCardUsageId;
	Date lineTransferDate;
	Double lineTransferAmount;
	Integer lineTransferTime;
	LineTransferType lineTransferType;
}