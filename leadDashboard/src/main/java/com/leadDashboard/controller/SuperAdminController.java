package com.leadDashboard.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.ChangePasswordDto;
import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.service.LeadService;
import com.leadDashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class SuperAdminController {
	private final UserService service;
	private final LeadService leadservice;
	
	@DeleteMapping("/DeleteUser")
	public ResponseEntity<Message<UserDto>> deleteUser(@RequestParam("UserId") int id) {
		log.info("In UserController getUserById() with request: {}", id);
		Message<UserDto> message = service.deleteUser(id);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}	
	@DeleteMapping("/deleteLead")
	public ResponseEntity<Message<LeadDto>>DeleteClient(@RequestParam("lid") int lid){
		log.info("In usercontroller login() with requesty:{}",lid);
		Message<LeadDto> message=leadservice.deleteLead(lid);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PostMapping("/ChangePassword")
	public ResponseEntity<Message<UserDto>> changePassword(@RequestBody ChangePasswordDto request) {
	 log.info("In UserController changePassword() with request: {}", request);
		Message<UserDto> message = service.changePassword(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}


}
