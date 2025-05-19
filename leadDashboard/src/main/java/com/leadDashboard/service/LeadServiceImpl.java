  package com.leadDashboard.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.LeadDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.mapper.LeadMapper;
import com.leadDashboard.model.Lead;
import com.leadDashboard.repository.LeadRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LeadServiceImpl implements LeadService {
	private final LeadMapper leadmapperimpl;
	private final LeadRepository leadrepository;
	@Override
	public Message<LeadDto> AddLead(LeadDto request) {
		Message<LeadDto> response = new Message<>();
		try {
			if(request == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.INVALID_LEAD_DATA);
				return response;	
			}
			Lead lead = leadmapperimpl.leadDtoToLead(request);
			leadrepository.save(lead); 
			LeadDto leadDto = leadmapperimpl.leadToLeadDto(lead);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.LEAD_ADDED);
			response.setData(leadDto);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<LeadDto> UpdateLead(LeadDto request) {
		Message<LeadDto> response = new Message<>();
		Lead lead = null;
		try {
			lead=leadrepository.getById(request.getLid());
			if(lead == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.LEAD_NOT_FOUND);
				return response;
			}
			if(lead == null || "True".equalsIgnoreCase(lead.getDeletedTag())) {	
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setResponseMessage(Constants.RECORD_NOT_FOUND);
	            return response;
	        }
			lead.setBDManagerAssigned(request.getBDManagerAssigned());
			lead.setClientName(request.getClientName());
			lead.setComments(request.getComments());
			lead.setContactNumber(request.getContactNumber());
			lead.setContactPerson(request.getContactPerson());
			lead.setDate(request.getDate());
			lead.setDateOfFutureContact(request.getDateOfFutureContact());
			lead.setDeletedTag(request.getDeletedTag());
			lead.setEmailId(request.getEmailId());
			lead.setEntryMadeBy(request.getEntryMadeBy());
			lead.setFuturePraposalDate(request.getFuturePraposalDate());
			lead.setInitialPraposalDate(request.getInitialPraposalDate());
			lead.setIsItLead(request.getIsItLead());
			lead.setLeadGenerationDate(request.getLeadGenerationDate());
			lead.setNameOfBDManager(request.getNameOfBDManager());
			lead.setOverAllStatus(request.getOverAllStatus());
			lead.setReferance(request.getReferance());
			lead.setStatus(request.getStatus());
			lead.setUpdatedStatusComments(request.getUpdatedStatusComments());
			lead.setWebsite(request.getWebsite());
			
			leadrepository.save(lead);
			LeadDto dto = leadmapperimpl.leadToLeadDto(lead);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.LEAD_UPDATED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error updating Lead:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
			return response;
		}
		
	}

	@Override
	public Message<LeadDto> GetLeadById(int lid) {
		Message<LeadDto> response = new Message<>();
		try {
			Lead lead = new Lead();
			lead=leadrepository.getById(lid);
			
			if(lead == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.LEAD_NOT_FOUND);
				return response;
			}
			if(lead == null || "True".equalsIgnoreCase(lead.getDeletedTag())) {	
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setResponseMessage(Constants.RECORD_NOT_FOUND);
	            return response;
	        }
			LeadDto dto = leadmapperimpl.leadToLeadDto(lead);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.LEAD_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Lead:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
			return response;
		}
		
		
		
	}

	@Override
	public  Map<String, Object> getAllLead() {
		Map<String, Object> responseMap = new LinkedHashMap<>(); 
	    try {
	        List<Lead> leads = leadrepository.findAll();

	        if (leads == null || leads.isEmpty()) {
	            responseMap.put("status", HttpStatus.NOT_FOUND);
	            responseMap.put("message", "No leads found");
	            responseMap.put("data", Collections.emptyList());
	            return responseMap;
	        }

	        List<LeadDto> leadDtos = leads.stream()
	                .map(leadmapperimpl::leadToLeadDto)
	                .collect(Collectors.toList());

	        responseMap.put("status", HttpStatus.OK);
	        responseMap.put("message", "Leads found successfully");
	        responseMap.put("data", leadDtos);
	        return responseMap;

	    } catch (Exception e) {
	        responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	        responseMap.put("message", "Something went wrong: " + e.getMessage());
	        responseMap.put("data", Collections.emptyList());
	        return responseMap;
	    }
	}


	@Override
	public Message<LeadDto> deleteLead(int lid) {
		Message<LeadDto> response = new Message<>();
		try {
			Lead lead = new Lead();
			lead=leadrepository.getById(lid);
			
			if(lead == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.LEAD_NOT_FOUND);
				return response;
			}
			if(lead == null || "True".equalsIgnoreCase(lead.getDeletedTag())) {	
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setResponseMessage(Constants.RECORD_NOT_FOUND);
	            return response;
	        }
			lead.setDeletedTag("True");
			leadrepository.save(lead);
			LeadDto dto = leadmapperimpl.leadToLeadDto(lead);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.LEAD_DELETED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error deleting Lead:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG + e.getMessage());
			return response;
		}
				
	}

}
