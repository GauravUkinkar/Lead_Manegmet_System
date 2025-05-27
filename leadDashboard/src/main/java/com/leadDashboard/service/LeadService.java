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
	 public Map<String, Object>getallStatusCount(String startDate,String endDate);
	 public Map<String, Object>getAllLeadByStatusUpdataedDateAndCurrentDat(String status,String updatedDate,String endDate);
	 public Map<String, Object>getAllUnassignedleads();
	 public Map<String, Object> getLeadsUpdatedInLast10Days();
	 public Map<String, Object>assingLead(int lid,int uid);

}
