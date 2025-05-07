package com.leadDashboard.mapper;

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.model.Lead;

public interface LeadMapper {
	public LeadDto leadToLeadDto(Lead lead);
	public Lead leadDtoToLead(LeadDto dto);
	

}
