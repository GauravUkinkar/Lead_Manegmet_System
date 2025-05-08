package com.leadDashboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.ContactchainDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.service.Contactchainservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/contactchaincontroller")
@Log4j2
@RequiredArgsConstructor
public class ContactChainController {
	private final Contactchainservice Service;
	
	@PostMapping("/addcontactchain")
	public ResponseEntity<Message<ContactchainDto>> addcontactchain(@RequestBody ContactchainDto request){
		log.info("In contactchaincontroller addcontactchain() with request:{}",request);
		Message<ContactchainDto> message=Service.addcontactchain(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PostMapping("/updatecontactchain")
	public ResponseEntity<Message<ContactchainDto>> updatecontactchain(@RequestBody ContactchainDto request){
		log.info("In contactchaincontroller updatecontactchain() with request:{}",request);
		Message<ContactchainDto> message=Service.updatecontactchain(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getcontactchain")
	public ResponseEntity<Message<ContactchainDto>> getcontactchain(@RequestParam ("cid") int cid){
		log.info("In contactchaincontroller getcontactchain() with request:{}",cid);
		Message<ContactchainDto> message=Service.getcontactchain(cid);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllcontactchain")
	public ResponseEntity<List<Message<ContactchainDto>>> getAllcontactchain(@RequestParam("page") int page,@RequestParam("size") int size){
		log.info("In contactchaincontroller getAllcontactchain()");
		List<Message<ContactchainDto>> message=Service.getAllcontactchain( page, size);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	@GetMapping("/getcontactchainByLid")
	public ResponseEntity<List<Message<ContactchainDto>>> getcontactchainByLid(@RequestParam ("lid") int lid){
		log.info("In contactchaincontroller getcontactchainByLid() with request:{}",lid);
		List<Message<ContactchainDto>> message=Service.getcontactchainByLid(lid);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
