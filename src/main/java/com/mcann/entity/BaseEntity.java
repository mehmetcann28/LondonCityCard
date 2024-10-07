package com.mcann.entity;

import com.mcann.utility.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {
	@Builder.Default
	@Enumerated(EnumType.STRING)
	State state = State.ACTIVE;
	@Builder.Default
	LocalDate createAt = LocalDate.now();
	@Builder.Default
	LocalDate updateAt = LocalDate.now();
}