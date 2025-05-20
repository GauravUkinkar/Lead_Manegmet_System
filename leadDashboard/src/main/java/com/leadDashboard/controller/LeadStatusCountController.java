package com.leadDashboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.service.LeadStatusCountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/leadcount")
@RequiredArgsConstructor

public class LeadStatusCountController {
	private final LeadStatusCountService leadstatuscountservice;
	
	@GetMapping("/lid")
	public ResponseEntity<List<LeadStatusCountDto>> getStatusCount() {
	    List<LeadStatusCountDto> dtoList = leadstatuscountservice.getLeadStatusCounts();
	    return ResponseEntity.ok(dtoList);
	}

}
