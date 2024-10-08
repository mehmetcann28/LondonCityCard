package com.mcann.controller;

import static com.mcann.constant.RestApis.*;
import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.entity.User;
import com.mcann.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(USER)
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/get-all-user")
	public List<User> allUser() {
		return userService.getAllUsers();
	}
	
	@PostMapping(REGISTER)
	public ResponseEntity<User> register(@RequestBody @Valid RegisterRequestDto dto){
		if (!dto.getPassword().equals(dto.getRePassword())){
			ResponseEntity.badRequest().body(null);
		}
		User user = userService.register(dto);
		return ResponseEntity.ok(user);
	}
	
}