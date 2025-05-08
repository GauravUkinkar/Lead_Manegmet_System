package com.leadDashboard.service;

import java.util.List;

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;

public interface LeadService {
	public Message<LeadDto>AddLead(LeadDto request);
	public Message<LeadDto>UpdateLead(LeadDto request);
	public Message<LeadDto>GetLeadById(int lid);
	public List<Message<LeadDto>>GetAllLead();
	public Message<LeadDto>deleteLead(int lid);
	

}
