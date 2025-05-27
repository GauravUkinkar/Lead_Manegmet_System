package com.leadDashboard.mapper;

import com.leadDashboard.Dto.EventDto;
import com.leadDashboard.model.Event;

public interface EventMapper {
	public EventDto eventToEventDto(Event event);
	public Event eventDtoToEvent(EventDto dto);
	

}
