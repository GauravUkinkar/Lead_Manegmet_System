package com.leadDashboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.StatusDto;
import com.leadDashboard.service.StatusService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/status")
@Log4j2
@RequiredArgsConstructor
public class StatusController {
	private final StatusService statusservice;
	
	@PostMapping("/addStatus")
	public ResponseEntity<Message<StatusDto>>AddClient(@RequestBody StatusDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<StatusDto> message=statusservice.AddStatus(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PutMapping("/updateStatus")
	public ResponseEntity<Message<StatusDto>>UpdateClient(@RequestBody StatusDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<StatusDto> message=statusservice.updateStatus(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@DeleteMapping("/deleteStatus")
	public ResponseEntity<Message<StatusDto>>DeleteClient(@RequestParam("id") int id){
		log.info("In usercontroller login() with request:{}",id);
		Message<StatusDto> message=statusservice.deleteStatus(id);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getStatusbyId")
	public ResponseEntity<Message<StatusDto>>GetClientById(@RequestParam("id") int id){
		log.info("In usercontroller login() with requesty:{}",id);
		Message<StatusDto> message=statusservice.getstatusById(id);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllStatus")
	public ResponseEntity<List<Message<StatusDto>>>GetAllClients(){
		List<Message<StatusDto>> message=statusservice.getAllStatus();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
