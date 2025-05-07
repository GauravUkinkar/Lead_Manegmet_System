package com.leadDashboard.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/superadmin")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class SuperAdminController {
	private final UserService service;
	
	@DeleteMapping("/DeleteUser")
	public ResponseEntity<Message<UserDto>> deleteUser(@RequestParam("UserId") int id) {
		log.info("In UserController getUserById() with request: {}", id);
		Message<UserDto> message = service.deleteUser(id);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}	

}
