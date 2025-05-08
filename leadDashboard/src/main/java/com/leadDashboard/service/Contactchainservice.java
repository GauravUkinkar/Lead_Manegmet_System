package com.leadDashboard.service;

import java.util.List;

import com.leadDashboard.Dto.ContactchainDto;
import com.leadDashboard.Dto.Message;

public interface Contactchainservice {
	public Message<ContactchainDto>addcontactchain(ContactchainDto request);
	public Message<ContactchainDto>updatecontactchain(ContactchainDto request);
	public Message<ContactchainDto>getcontactchain(Integer id);
	public List<Message<ContactchainDto>>getAllcontactchain(Integer page, Integer size);
	public List<Message<ContactchainDto>>getcontactchainByLid(Integer lid);

}
