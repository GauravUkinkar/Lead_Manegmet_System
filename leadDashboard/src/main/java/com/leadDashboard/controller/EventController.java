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

import com.leadDashboard.Dto.EventDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/event")
@Log4j2
@RequiredArgsConstructor

public class EventController {
	private final EventService eventservice;
	
	@PostMapping("/addEvent")
	public ResponseEntity<Message<EventDto>> AddEvent(@RequestBody EventDto request) {
		log.info("In usercontroller login() with request:{}",request);
		Message<EventDto> message=eventservice.AddEvent(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PutMapping("/updateEvent")
	public ResponseEntity<Message<EventDto>> UpdateEvent(@RequestBody EventDto request) {
		log.info("In usercontroller login() with request:{}",request);
		Message<EventDto> message=eventservice.UpdateEvent(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@DeleteMapping("/deleteEvent")
	public ResponseEntity<Message<EventDto>> DeleteEvent(@RequestParam("eId") int eId) {
		log.info("In usercontroller login() with request:{}",eId);
		Message<EventDto> message=eventservice.DeleteEvent(eId);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getEventById")
	public ResponseEntity<Message<EventDto>> GetEventById(@RequestParam("eId") int eId) {
		log.info("In usercontroller login() with request:{}",eId);
		Message<EventDto> message=eventservice.GetEventById(eId);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getEventByName")
	public ResponseEntity<Message<EventDto>> GetEventByName(@RequestParam("name") String name) {
		log.info("In usercontroller login() with request:{}",name);
		Message<EventDto> message=eventservice.GetEventByName(name);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getEventByLocation")
	public ResponseEntity<Message<EventDto>> GetEventByLocation(@RequestParam("location") String location) {
		log.info("In usercontroller login() with request:{}",location);
		Message<EventDto> message=eventservice.GetEventsByLocation(location);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getAllEvents")
	public ResponseEntity<List<Message<EventDto>>> GetallEvents() {
		List<Message<EventDto>> message=eventservice.GetAllEvents();
		return ResponseEntity.status(HttpStatus.OK).body(message);
		
		
	}

}
