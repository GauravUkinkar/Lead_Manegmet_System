package com.leadDashboard.mapper;

import org.springframework.stereotype.Component;

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.model.Lead;
@Component

public class LeadMapperImpl implements LeadMapper {

	@Override
	public LeadDto leadToLeadDto(Lead lead) {
		LeadDto dto = new LeadDto();
		dto.setClientName(lead.getClientName());
		dto.setComments(lead.getComments());
		dto.setContactNumber(lead.getContactNumber());
		dto.setContactPerson(lead.getContactPerson());
		dto.setDate(lead.getDate());
		dto.setDateOfFutureContact(lead.getDateOfFutureContact());
		dto.setEmailId(lead.getEmailId());
		dto.setEntryMadeBy(lead.getEntryMadeBy());
		dto.setIsItLead(lead.getIsItLead());
		dto.setLeadGenerationDate(lead.getLeadGenerationDate());
		dto.setLid(lead.getLid());
		dto.setNameOfBDManager(lead.getNameOfBDManager());
		dto.setOverAllStatus(lead.getOverAllStatus());
		dto.setReferance(lead.getReferance());
		dto.setStatus(lead.getStatus());
		dto.setWebsite(lead.getWebsite());
		dto.setUpdatedDate(lead.getUpdatedDate());
		return dto;
		
		
		
	}

	@Override
	public Lead leadDtoToLead(LeadDto dto) {
		return new Lead()
				.setClientName(dto.getClientName())
				.setComments(dto.getComments())
				.setContactNumber(dto.getContactNumber())
				.setContactPerson(dto.getContactPerson())
				.setDate(dto.getDate())
				.setDateOfFutureContact(dto.getDateOfFutureContact())
				.setEmailId(dto.getEmailId())
				.setEntryMadeBy(dto.getEntryMadeBy())
				.setIsItLead(dto.getIsItLead())
				.setLeadGenerationDate(dto.getLeadGenerationDate())
				.setNameOfBDManager(dto.getNameOfBDManager())
				.setOverAllStatus(dto.getOverAllStatus())
				.setReferance(dto.getReferance())
				.setStatus(dto.getStatus())
				.setWebsite(dto.getWebsite())
				.setUpdatedDate(dto.getUpdatedDate());
		
	}

}
