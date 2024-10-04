package com.mcann.service;

import com.mcann.entity.User;
import com.mcann.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
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
	
}