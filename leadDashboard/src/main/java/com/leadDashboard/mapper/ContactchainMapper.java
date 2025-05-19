package com.leadDashboard.mapper;

import com.leadDashboard.Dto.ContactchainDto;
import com.leadDashboard.model.Contactchain;

public interface ContactchainMapper {
public Contactchain dtotoEntity(ContactchainDto dto);
public ContactchainDto modeltoDto(Contactchain entity);
}
