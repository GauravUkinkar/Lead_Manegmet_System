package com.leadDashboard.mapper;

import org.springframework.stereotype.Component;

import com.leadDashboard.Dto.EventDto;
import com.leadDashboard.model.Event;
@Component

public class EventMapperImpl implements EventMapper {

	@Override
	public EventDto eventToEventDto(Event event) {
		return new EventDto().setAttended(event.getAttended())
				.setComments(event.getComments())
				.setDate(event.getDate())
				.setDiscription(event.getDiscription())
				.setDuration(event.getDuration())
				.setEId(event.getEId())
				.setEntryFee(event.getEntryFee())
				.setLinks(event.getLinks())
				.setLocation(event.getLocation())
				.setName(event.getName())
				.setTime(event.getTime());
		
	}

	@Override
	public Event eventDtoToEvent(EventDto dto) {
		return new Event().setAttended(dto.getAttended())
				.setComments(dto.getComments())
				.setDate(dto.getDate())
				.setDiscription(dto.getDiscription())
				.setDuration(dto.getDuration())
				.setEntryFee(dto.getEntryFee())
				.setLinks(dto.getLinks())
				.setLocation(dto.getLocation())
				.setName(dto.getName())
				.setTime(dto.getTime());
		
	}

}
