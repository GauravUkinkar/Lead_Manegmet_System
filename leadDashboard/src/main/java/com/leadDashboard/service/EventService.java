package com.leadDashboard.service;

import java.util.List;

import com.leadDashboard.Dto.EventDto;
import com.leadDashboard.Dto.Message;

public interface EventService {
	public Message<EventDto>AddEvent(EventDto request);
	public Message<EventDto>UpdateEvent(EventDto request);
	public Message<EventDto>DeleteEvent(int eId);
	public Message<EventDto>GetEventById(int eId);
	public Message<EventDto>GetEventByName(String name);
	public Message<EventDto>GetEventsByLocation(String location);
	public List<Message<EventDto>>GetAllEvents();
	

}
