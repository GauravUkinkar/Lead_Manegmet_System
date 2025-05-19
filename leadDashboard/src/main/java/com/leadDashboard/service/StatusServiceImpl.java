package com.leadDashboard.service;




import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.StatusDto;
import com.leadDashboard.model.Status;
import com.leadDashboard.repository.StatusRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatusServiceImpl implements StatusService {
private final StatusRepository statusRepository;
	@Override
	public Message<StatusDto> saveStatus(StatusDto statusDto) {
		Message<StatusDto> message=new Message<StatusDto>();
		Status status=null;
		StatusDto statusDto1=null;
		try {
			
			status.setStatus(statusDto.getStatus());
			statusRepository.save(status);
			statusDto1.setId(status.getId());
			statusDto1.setStatus(status.getStatus());
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.SUCCESS);
			message.setData(statusDto1);
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}
	@Override
	public Message<StatusDto> AddStatus(StatusDto statusDto) {
		Message<StatusDto> response = new Message<>();
		try {
			if (statusDto == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.INVALID_STATUS_DATA);
				return response;
			}
			Status status = new Status();
			status.setId(statusDto.getId());
			status.setStatus(statusDto.getStatus());
			
			statusRepository.save(status);
			
			StatusDto dto = new StatusDto();
			dto.setId(status.getId());
			dto.setStatus(status.getStatus());
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.STATUS_ADDED);
			response.setData(dto);
			return response;
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
			return response;
		}
		
	}
	@Override
	public Message<StatusDto> getstatusById(Integer id) {
	    Message<StatusDto> response = new Message<>();
	    try {
	        // Fetch entity from database
	        Status status = statusRepository.getById(id);

	        if (status == null) {
	            response.setStatus(HttpStatus.BAD_REQUEST);
	            response.setResponseMessage(Constants.STATUS_NOT_FOUND);
	            return response;
	        }

	        // Prepare DTO
	        StatusDto dto = new StatusDto();
	        dto.setId(status.getId());
	        dto.setStatus(status.getStatus());

	        // Prepare response
	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage(Constants.STATUS_FOUND);
	        response.setData(dto);
	        return response;

	    } catch (Exception e) {
	        System.err.println("Error fetching Status: " + e.getMessage());
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
	        return response;
	    }
	}

	@Override
	public Message<StatusDto> updateStatus(StatusDto statusDto) {
		Message<StatusDto> response = new Message<>();
		Status status = null;
		try {
			status=statusRepository.getById(statusDto.getId());
			if(status == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.STATUS_NOT_FOUND);
				return response;
			}
			status.setStatus(statusDto.getStatus());
			statusRepository.save(status);
			
			StatusDto dto = new StatusDto();
			dto.setStatus(status.getStatus());
			dto.setId(status.getId());
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.STATUS_UPDATED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error Updating Status:" +e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
			return response;
		}
		
		
	}
	@Override
	public Message<StatusDto> deleteStatus(Integer id) {
		Message<StatusDto> response = new Message<>();
		try {
			Status status = new Status();
			status = statusRepository.getById(id);
			if (status == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.STATUS_NOT_FOUND);
				return response;
			}
			StatusDto dto = new StatusDto();
			dto.setId(status.getId());
			dto.setStatus(status.getStatus());
			
			statusRepository.deleteById(id);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.STATUS_DELETED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG+ e.getMessage());
			return response;
		}
		
	}
	@Override
	public Map<String, Object> getAllStatus() {
	    Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {
	        List<Status> statuses = statusRepository.findAll();

	        List<StatusDto> dtoList = statuses.stream().map(status -> {
	            StatusDto dto = new StatusDto();
	            dto.setId(status.getId());
	            dto.setStatus(status.getStatus());
	            return dto;
	        }).collect(Collectors.toList());

	        responseMap.put("status", HttpStatus.OK);
	        responseMap.put("message", "Status found successfully");
	        responseMap.put("data", dtoList);

	    } catch (Exception e) {
	        responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	        responseMap.put("message", Constants.SOMETHING_WENT_WRONG + ": " + e.getMessage());
	        responseMap.put("data", Collections.emptyList());
	    }
	    return responseMap;
	}

	}
		    

