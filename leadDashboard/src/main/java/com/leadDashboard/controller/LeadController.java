package com.leadDashboard.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leadDashboard.Dto.ChangePasswordDto;
import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.service.LeadService;
import com.leadDashboard.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/lead")
@Log4j2
@RequiredArgsConstructor

public class LeadController {
	private final LeadService leadservice;
	private final UserService service;
	
	@PostMapping("/addLead")
	public ResponseEntity<Message<LeadDto>>AddClient(@RequestBody LeadDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<LeadDto> message=leadservice.AddLead(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
		
		@PostMapping("/upload")
	    public ResponseEntity<String> uploadCsv(@RequestPart("file") MultipartFile file) {
	        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
	            return ResponseEntity.badRequest().body("Please upload a valid CSV file.");
	        }

	        leadservice.saveLeadsFromCsv(file);
	        return ResponseEntity.ok("CSV uploaded and data saved successfully.");
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
	public ResponseEntity<Map<String, Object>> getAllLeads() {
	    log.info("In LeadController getAllLeads()");
	    Map<String, Object> response = leadservice.getAllLead();

	    HttpStatus status = HttpStatus.OK;
	    if (response.get("status") instanceof HttpStatus) {
	        status = (HttpStatus) response.get("status");
	    }

	    return new ResponseEntity<>(response, status);
	}	@PostMapping("/ChangePassword")
	public ResponseEntity<Message<UserDto>> changePassword(@RequestBody ChangePasswordDto request) {
	 log.info("In UserController changePassword() with request: {}", request);
		Message<UserDto> message = service.changePassword(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/lead-status-count")
	public ResponseEntity<Map<String, Object>> getLeadStatusCounts(
	        @RequestParam String startDate,
	        @RequestParam String endDate) {
		Map<String, Object> response = leadservice.getallStatusCount(startDate,endDate);

	    HttpStatus status = HttpStatus.OK;
	    if (response.get("status") instanceof HttpStatus) {
	        status = (HttpStatus) response.get("status");
	    }

	    return new ResponseEntity<>(response, status);
	}
	@GetMapping("/lead-statusFilter")
	public ResponseEntity<Map<String, Object>> getLeadByfilter(
			@RequestParam String status,
	        @RequestParam String startDate,
	        @RequestParam String endDate
	        ) {
		Map<String, Object> response = leadservice.getAllLeadByStatusUpdataedDateAndCurrentDat(status,startDate,endDate);

	    HttpStatus responsestatus = HttpStatus.OK;
	    if (response.get("Httpstatus") instanceof HttpStatus) {
	    	responsestatus = (HttpStatus) response.get("Httpstatus");
	    }

	    return new ResponseEntity<>(response, responsestatus);
	}
	
//	@GetMapping ("/UnassignedLeads")
//	public ResponseEntity<Map<String, Object>> UnAssignedLeads(
//	        ) {
//		Map<String, Object> response = leadservice.getAllUnassignedleads();
//
//	    HttpStatus responsestatus = HttpStatus.OK;
//	    if (response.get("Httpstatus") instanceof HttpStatus) {
//	    	responsestatus = (HttpStatus) response.get("Httpstatus");
//	    }
//
//	    return new ResponseEntity<>(response, responsestatus);
//	}
	@GetMapping("/lead-recent-updates")
	public ResponseEntity<Map<String, Object>> getRecentLeads() {
	    Map<String, Object> response = leadservice.getLeadsUpdatedInLast10Days();

	    HttpStatus status = HttpStatus.OK;
	    if (response.get("Httpstatus") instanceof HttpStatus) {
	        status = (HttpStatus) response.get("Httpstatus");
	    }

	    return new ResponseEntity<>(response, status);
	}
	@PostMapping("/AssignLead")
	public ResponseEntity<Map<String, Object>> assignLead(
			@RequestParam int lid,
	        @RequestParam int id
	        ) {
		Map<String, Object> response = leadservice.assingLead(lid,id);

	    HttpStatus responsestatus = HttpStatus.OK;
	    if (response.get("Httpstatus") instanceof HttpStatus) {
	    	responsestatus = (HttpStatus) response.get("Httpstatus");
	    }

	    return new ResponseEntity<>(response, responsestatus);
	}
	
}
