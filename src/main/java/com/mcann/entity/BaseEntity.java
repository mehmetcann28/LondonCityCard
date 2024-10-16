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
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Builder.Default
	@Enumerated(EnumType.STRING)
	State state = State.ACTIVE;
	@Builder.Default
	LocalDateTime createAt = LocalDateTime.now();
	@Builder.Default
	LocalDateTime updateAt = LocalDateTime.now();
}