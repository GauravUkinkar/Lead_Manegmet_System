package com.leadDashboard.service;

import java.util.List;



import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.Dto.Message;

public interface LeadStatusCountService {
	public List<Message<LeadStatusCountDto>> getLeadStatusCounts();
	
	

}
