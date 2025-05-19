package com.leadDashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.ChangePasswordDto;
import com.leadDashboard.Dto.LoginRequest;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.RegistrationDto;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@PostMapping("/RegisterUser")
	public ResponseEntity<Message<UserDto>> registerUser(@RequestBody RegistrationDto user) {
		log.info("In UserController registerUser() with request: {}", user);
		Message<UserDto> message = service.registerUser(user);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PostMapping("/LoginUser")
	public ResponseEntity<Message<UserDto>> loginUser(@RequestBody LoginRequest request) {
		log.info("In UserController loginUser() with request: {}", request);
		Message<UserDto> message = service.loginUser(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PostMapping("/SendOtp")
	public ResponseEntity<Message<UserDto>> sendOTP(@RequestParam("Email") String email) {
		log.info("In UserController sendOTP() with request: {}", email);
		Message<UserDto> message = service.sendOtp(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PostMapping("/VerifyOtpAndChangePassword")
	public ResponseEntity<Message<UserDto>> verifyOTPAndChangePassword(@RequestBody ChangePasswordDto request) {
		log.info("In UserController verifyOTP() with request: {}", request.getEmail());
		Message<UserDto> message = service.updatePassword(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/GetAllUsers")
	public ResponseEntity<Map<String, Object>> getAllUsers(
	        @RequestParam(value = "Page", required = false) Integer page,
	        @RequestParam(value = "Size", required = false) Integer size) {

	    log.info("In UserController getAllUsers()");
	    Map<String, Object> response = service.getAllUsers(page, size);

	    // Extract HttpStatus from the response
	    HttpStatus status = HttpStatus.OK;
	    if (response.get("status") instanceof HttpStatus) {
	        status = (HttpStatus) response.get("status");
	    }

	    return new ResponseEntity<>(response, status);
	}
}
