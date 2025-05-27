package com.leadDashboard.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.TargetDto;
import com.leadDashboard.service.TargetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Target")
@Log4j2
@RequiredArgsConstructor
public class TargetController {
	private final TargetService service;

	@PostMapping("/AddTarget")
	public ResponseEntity<Map<String, Object>> assignLead(@RequestBody TargetDto dto) {
		Map<String, Object> response = service.AddTarget(dto);

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}

		return new ResponseEntity<>(response, responsestatus);
	}
	
	@PostMapping("/UpdateTarget")
	public ResponseEntity<Map<String, Object>> UpdateTarget(@RequestBody TargetDto dto) {
		Map<String, Object> response = service.updateTarget(dto);

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}
		return new ResponseEntity<>(response, responsestatus);
	}
	
	@GetMapping("/GetAllTargets")
	public ResponseEntity<Map<String, Object>> GetAllTargets() {
		Map<String, Object> response = service.getAllTYarget();

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}
		return new ResponseEntity<>(response, responsestatus);
	}
	@GetMapping("/GetTargetByuid")
	public ResponseEntity<Map<String, Object>> GetTargetById(@RequestParam int uid) {
		Map<String, Object> response = service.getAllTargetByuid(uid);

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}
		return new ResponseEntity<>(response, responsestatus);
	}
	@GetMapping("/GetTargetBymonthAndYear")
	public ResponseEntity<Map<String, Object>> GetTargetBylid(@RequestParam String month,@RequestParam String year) {
		Map<String, Object> response = service.getAllTargetBymonthAndYear(month,year);

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}
		return new ResponseEntity<>(response, responsestatus);
	}
	
	@GetMapping("/GetTargetForSpecificUserFilter")
	public ResponseEntity<Map<String, Object>> GetTargetBylid(@RequestParam String month,@RequestParam String year,@RequestParam int uid) {
		Map<String, Object> response = service.getAllTargetBymonthAndYearAnduid(month,year,uid);

		HttpStatus responsestatus = HttpStatus.OK;
		if (response.get("Httpstatus") instanceof HttpStatus) {
			responsestatus = (HttpStatus) response.get("Httpstatus");
		}
		return new ResponseEntity<>(response, responsestatus);
	}

}
