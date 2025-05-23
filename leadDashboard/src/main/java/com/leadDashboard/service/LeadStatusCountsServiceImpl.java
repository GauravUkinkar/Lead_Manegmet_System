package com.leadDashboard.service;




import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.repository.LeadRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadStatusCountsServiceImpl implements LeadStatusCountService {
	private final LeadRepository leadrepository;

	@Override
	public List<Message<LeadStatusCountDto>> getLeadStatusCounts() {
		Message<LeadStatusCountDto> response = new Message<>();
		List<LeadStatusCountDto> dto = leadrepository.findLeadStatusCount();
		response.setStatus(HttpStatus.OK);	
		response.setResponseMessage(Constants.RECORD_FOUND);
		response.setData(dto);
		return response;
		

	} 
}





