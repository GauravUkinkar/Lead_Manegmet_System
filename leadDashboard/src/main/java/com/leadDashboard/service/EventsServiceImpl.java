package com.leadDashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.EventDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.mapper.EventMapper;
import com.leadDashboard.model.Event;
import com.leadDashboard.repository.EventRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventService {
	private final EventRepository eventrepository;
	private final EventMapper eventmapperimpl;
	@Override
	public Message<EventDto> AddEvent(EventDto request) {
		Message<EventDto> response = new Message<>();
		try {
			if(request == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.INVALID_EVENT_DATA);
				return response;
			}
			Event event = eventmapperimpl.eventDtoToEvent(request);
			eventrepository.save(event);
			EventDto dto = eventmapperimpl.eventToEventDto(event);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.EVENT_ADDED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
			
		}
		
	}

	@Override
	public Message<EventDto> UpdateEvent(EventDto request) {
		Message<EventDto> response = new Message<>();
		Event event = null;
		try {
			event=eventrepository.getById(request.getEId());
			if(event == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.EVENT_NOT_FOUND);
				return response;
			}
			event.setAttended(request.getAttended());
			event.setComments(request.getComments());
			event.setDate(request.getDate());
			event.setDiscription(request.getDiscription());
			event.setDuration(request.getDuration());
			event.setEntryFee(request.getEntryFee());
			event.setLinks(request.getLinks());
			event.setLocation(request.getLocation());
			event.setName(request.getName());
			event.setTime(request.getTime());
			event.setTime(request.getTime());
			
			eventrepository.save(event);
			EventDto dto = eventmapperimpl.eventToEventDto(event);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.EVENT_UPDATED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error updating Event:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<EventDto> DeleteEvent(int eId) {
		Message<EventDto> response = new Message<>();
		try {
			Event event = new Event();
			event = eventrepository.getById(eId);
			if(event == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.EVENT_NOT_FOUND);
				return response;
			}
			EventDto dto = eventmapperimpl.eventToEventDto(event);
			eventrepository.deleteById(eId);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.EVENT_DELETED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<EventDto> GetEventById(int eId) {
		Message<EventDto> response = new Message<>();
		try {
			Event event = new Event();
			event=eventrepository.getById(eId);
			
			if(event == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.EVENT_NOT_FOUND);
				return response;
			}
			EventDto dto = eventmapperimpl.eventToEventDto(event);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.EVENT_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Event:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<EventDto> GetEventByName(String name) {
		    Message<EventDto> response = new Message<>();
		    try {
		        Event event = eventrepository.getByName(name);

		        if (event == null) {
		            response.setStatus(HttpStatus.BAD_REQUEST);
		            response.setResponseMessage(Constants.EVENT_NOT_FOUND);
		            return response;
		        }

		        EventDto dto = eventmapperimpl.eventToEventDto(event);
		        response.setStatus(HttpStatus.OK);
		        response.setResponseMessage(Constants.EVENT_FOUND);
		        response.setData(dto);
		        return response;

		    } catch (Exception e) {
		        System.err.println("Error fetching Event by Name: " + e.getMessage());
		        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
		        return response;
		    }
		}

	@Override
	public Message<EventDto> GetEventsByLocation(String location) {
	    Message<EventDto> response = new Message<>();
	    try {
	        Event event = eventrepository.getByLocation(location);

	        if (event == null) {
	            response.setStatus(HttpStatus.BAD_REQUEST);
	            response.setResponseMessage(Constants.EVENT_NOT_FOUND);
	            return response;
	        }

	        EventDto dto = eventmapperimpl.eventToEventDto(event);
	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage(Constants.EVENT_FOUND);
	        response.setData(dto);
	        return response;

	    } catch (Exception e) {
	        System.err.println("Error fetching Event by Location: " + e.getMessage());
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
	        return response;
	    }
	}

	@Override
	public List<Message<EventDto>> GetAllEvents() {
		List<Message<EventDto>> message = new ArrayList<>();
		try {
			List<Event> events = eventrepository.findAll();
			for(Event event : events) {
				EventDto dto = eventmapperimpl.eventToEventDto(event);
				
				message.add(new Message<EventDto>(HttpStatus.OK,"Events Found Successfully",dto));
			}
			return message;
		}catch (Exception e) {
				message.add(new Message<EventDto>(HttpStatus.INTERNAL_SERVER_ERROR,
						Constants.SOMETHING_WENT_WRONG +e.getMessage(),null));
				return message;
			}
	}
}
		
		
	


