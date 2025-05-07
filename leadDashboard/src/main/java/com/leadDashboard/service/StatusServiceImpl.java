package com.leadDashboard.service;



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

}
