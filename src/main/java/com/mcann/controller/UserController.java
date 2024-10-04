package com.mcann.controller;

import com.mcann.entity.User;
import com.mcann.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/get-all-user")
	public List<User> allUser() {
		return userService.getAllUsers();
	}
	
}