package com.leadDashboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.service.LeadStatusCountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/leadcount")
@Log4j2
@RequiredArgsConstructor

public class LeadStatusCountController {
	private final LeadStatusCountService leadstatuscountservice;
	
	@GetMapping("/lid")
    public ResponseEntity<Message<List<LeadStatusCountDto>>> getLeadStatusCounts() {
        Message<List<LeadStatusCountDto>> response = leadstatuscountservice.getLeadStatusCounts();
        return new ResponseEntity<>(response, response.getStatus());
    }

//	    List<LeadStatusCountDto> dtoList = leadstatuscountservice.getLeadStatusCounts();
//	    return ResponseEntity.ok(dtoList);
	}
