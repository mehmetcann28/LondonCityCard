package com.mcann.entity;

import com.mcann.utility.enums.LineTransferType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_line_transfer")
public class LineTransfer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long firstCardUsageId;
	Long nextCardUsageId;
	Long lineTransferDate;
	Long lineTransferTime;
	LineTransferType lineTransferType;
}