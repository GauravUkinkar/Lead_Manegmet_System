package com.leadDashboard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.ContactchainDto;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.mapper.ContactchainMapper;
import com.leadDashboard.model.Contactchain;
import com.leadDashboard.model.Lead;
import com.leadDashboard.repository.ContactChainRepo;
import com.leadDashboard.repository.LeadRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContactchainserviceImpl implements Contactchainservice {

	private final ContactChainRepo contactchainRepository;
	private final ContactchainMapper mapper;
	private final LeadRepository leadRepository;
	@Override
	public Message<ContactchainDto> addcontactchain(ContactchainDto request) {
		Message<ContactchainDto> response = new Message<ContactchainDto>();
		Contactchain chain=null;
		Lead lead=null;
		try {
			lead= leadRepository.getById(request.getLid());
			if(lead==null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.LEAD_NOT_FOUND);
				return response;
			}
			chain=contactchainRepository.save(mapper.dtotoEntity(request));
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.CONTACT_CHAIN_ADDED);
			response.setData(mapper.modeltoDto(chain));
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}
	@Override
	public Message<ContactchainDto> updatecontactchain(ContactchainDto request) {
		Message<ContactchainDto> response = new Message<ContactchainDto>();
		try {
			Contactchain chain=contactchainRepository.save(mapper.dtotoEntity(request));
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.CONTACT_CHAIN_UPDATED);
			response.setData(mapper.modeltoDto(chain));
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}
	@Override
	public Message<ContactchainDto> getcontactchain(Integer id) {
		Message<ContactchainDto> response = new Message<ContactchainDto>();
		try {
			Contactchain chain=contactchainRepository.getById(id);
			if(chain==null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(Constants.CONTACT_CHAIN_NOT_FOUND);
				return response;
			}
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(Constants.CONTACT_CHAIN_FOUND);
			response.setData(mapper.modeltoDto(chain));
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(Constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}
	@Override
	public Map<String, Object> getAllContactChain(Integer page, Integer size) {
	    Map<String, Object> responseMap = new LinkedHashMap<>(); // maintains insertion order
	    try {
	        int pageNumber = (page == null || page <= 0) ? 0 : page - 1;
	        int pageSize = (size == null || size <= 0) ? 10 : size;

	        Pageable pageable = PageRequest.of(pageNumber, pageSize);
	        Page<Contactchain> contacts = contactchainRepository.findAll(pageable);

	        if (contacts == null || contacts.isEmpty()) {
	            responseMap.put("status", HttpStatus.NOT_FOUND);
	            responseMap.put("message", Constants.RECORD_NOT_FOUND);
	            responseMap.put("data", Collections.emptyList());
	            return responseMap;
	        }

	        List<ContactchainDto> contactDtos = contacts.stream()
	                .map(mapper::modeltoDto)
	                .collect(Collectors.toList());

	        responseMap.put("status", HttpStatus.OK);
	        responseMap.put("message", Constants.USER_FOUND);
	        responseMap.put("data", contactDtos);
	        return responseMap;

	    } catch (Exception e) {
	        log.error(Constants.SOMETHING_WENT_WRONG + " " + e.getMessage());
	        responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	        responseMap.put("message", e.getMessage());
	        responseMap.put("data", Collections.emptyList());
	        return responseMap;
	    }
	}
	@Override
	public List<Message<ContactchainDto>> getcontactchainByLid(Integer lid) {
		 List<Message<ContactchainDto>> messages = new ArrayList<>();
		    try {
		        List<Contactchain> contactchains = contactchainRepository.findByLid(lid);
		        
		        for (Contactchain contact : contactchains) {
		            ContactchainDto dto = new ContactchainDto();
		            // Manually set fields, or use a mapper like ModelMapper or MapStruct
		            dto.setCid(contact.getCid());
		            dto.setContactDate(contact.getContactDate());
		            dto.setFeedback(contact.getFeedback());
		            dto.setFollowUpDate(contact.getFollowUpDate());
		            dto.setLid(contact.getLid());
		            dto.setMethod(contact.getMethod());
		            dto.setResponse(contact.getResponse());
		            // ... set other fields as needed

		            Message<ContactchainDto> message = new Message<>();
		            message.setData(dto);  // assuming `data` is your response body field
		            message.setStatus(HttpStatus.OK);
		            messages.add(message);
		        }
		    } catch (Exception e) {
		        Message<ContactchainDto> errorMessage = new Message<>();
		        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        errorMessage.setResponseMessage(Constants.SOMETHING_WENT_WRONG + ": " + e.getMessage());
		        messages.add(errorMessage);
		    }
		    return messages;
		}

}
