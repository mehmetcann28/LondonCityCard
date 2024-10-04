package com.mcann.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	String address;
	String username;
	LocalDate birthday;
}