package com.leadDashboard.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.repository.LeadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadStatusCountsServiceImpl implements LeadStatusCountService {
	private final LeadRepository leadrepository;

	@Override
	public List<LeadStatusCountDto> getLeadStatusCounts() {
		return leadrepository.findLeadStatusCount();
	}
	
}



