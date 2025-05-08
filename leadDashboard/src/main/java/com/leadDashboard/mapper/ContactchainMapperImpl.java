package com.leadDashboard.mapper;

import org.springframework.stereotype.Component;

import com.leadDashboard.Dto.ContactchainDto;
import com.leadDashboard.model.Contactchain;

@Component
public class ContactchainMapperImpl implements ContactchainMapper {

	@Override
	public Contactchain  dtotoEntity(ContactchainDto dto) {
		return new Contactchain()
				.setContactDate(dto.getContactDate())
				.setFeedback(dto.getFeedback())
				.setFollowUpDate(dto.getFollowUpDate())
				.setLid(dto.getLid())
				.setMethod(dto.getMethod())
				.setResponse(dto.getResponse());
	}

	@Override
	public ContactchainDto modeltoDto(Contactchain entity) {
		return new ContactchainDto()
				.setCid(entity.getCid())
				.setContactDate(entity.getContactDate())
				.setFeedback(entity.getFeedback())
				.setFollowUpDate(entity.getFollowUpDate())
				.setLid(entity.getLid())
				.setMethod(entity.getMethod())
				.setResponse(entity.getResponse());
	}

}
