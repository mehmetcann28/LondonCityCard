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
	
	@GetMapping("/add-user")
	public String addUser() {
		userService.addUser(1L, "Mehmet", "Tufan", "m.tufan@gmail.com", "123456", "05225473366", "Adana", "mtufan", LocalDate.of(1992,10,10));
		userService.addUser(2L,"Ozkan","Sargin","o.sargin@gmail.com","123456","05356998877","Karadag","osargin",LocalDate.of(1991,02,14));
		userService.addUser(3L,"Mehmet Can","Karahan","mcan@gmail.com","123456","05368254788","Istanbul","mcan",LocalDate.of(2000,06,19));
		return "Kullanicilar eklendi";
	}
	
	@GetMapping("/get-all-user")
	public List<User> allUser() {
		return userService.getAllUsers();
	}
	
}