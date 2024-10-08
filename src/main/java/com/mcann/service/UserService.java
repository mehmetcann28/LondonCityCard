package com.mcann.service;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.entity.User;
import com.mcann.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public void addUser(String firstName, String lastName, String email, String password, String phone, String address, String username, LocalDate birthday) {
		User user = User.builder()
				.firstName(firstName)
				.lastName(lastName)
				.address(address)
				.email(email)
				.birthday(birthday)
				.username(username)
				.password(password)
				.phone(phone)
				        .build();
		userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User register(RegisterRequestDto dto){
		return userRepository.save(User.builder()
				                    .firstName(dto.getFirstName())
				                    .lastName(dto.getLastName())
				                    .address(dto.getAddress())
				                    .email(dto.getEmail())
				                    .username(dto.getUsername())
				                    .password(dto.getPassword())
				                       .phone(dto.getPhone())
				                       .birthday(dto.getBirthday())
				                    .build());
		
	}
	
}