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

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.service.LeadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/lead")
@Log4j2
@RequiredArgsConstructor

public class LeadController {
	private final LeadService leadservice;
	
	@PostMapping("/addLead")
	public ResponseEntity<Message<LeadDto>>AddClient(@RequestBody LeadDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<LeadDto> message=leadservice.AddLead(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PutMapping("/updateLead")
	public ResponseEntity<Message<LeadDto>>UpdateClient(@RequestBody LeadDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<LeadDto> message=leadservice.UpdateLead(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getLeadbyId")
	public ResponseEntity<Message<LeadDto>>GetClientById(@RequestParam("lid") int lid){
		log.info("In usercontroller login() with requesty:{}",lid);
		Message<LeadDto> message=leadservice.GetLeadById(lid);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllLead")
	public ResponseEntity<List<Message<LeadDto>>>GetAllLead(){
		List<Message<LeadDto>> message=leadservice.GetAllLead();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	@DeleteMapping("/deleteLead")
	public ResponseEntity<Message<LeadDto>>DeleteClient(@RequestParam("lid") int lid){
		log.info("In usercontroller login() with requesty:{}",lid);
		Message<LeadDto> message=leadservice.deleteLead(lid);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}


}
