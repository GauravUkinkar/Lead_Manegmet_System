package com.leadDashboard.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;

public interface LeadService {
	public Message<LeadDto>AddLead(LeadDto request);
	public Message<LeadDto>UpdateLead(LeadDto request);
	public Message<LeadDto>GetLeadById(int lid);
	public Map<String, Object>getAllLead();
	public Message<LeadDto>deleteLead(int lid);
	 public void saveLeadsFromCsv(MultipartFile file);
	

}
