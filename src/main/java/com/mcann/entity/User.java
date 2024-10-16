package com.mcann.entity;

import com.mcann.utility.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long cardId;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	String address;
	@Column(length = 64, nullable = false)
	String username;
	LocalDate birthday;
	@Enumerated(EnumType.STRING)
	CardType cardType;
//	String customField;
}