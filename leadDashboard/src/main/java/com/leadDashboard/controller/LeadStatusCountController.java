package com.leadDashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	    public ResponseEntity<LeadStatusCountDto> getStatusCount(  ) {
	    	LeadStatusCountDto dto = leadstatuscountservice.getLeadStatusCounts();
	        return ResponseEntity.ok(dto);
	    }

}
