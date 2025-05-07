package com.leadDashboard.service;

import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.StatusDto;

public interface StatusService {
 public Message	 <StatusDto>saveStatus(StatusDto statusDto);
// public Message<StatusDto>getstatus(Integer id);
// public Message<StatusDto>updateStatus(StatusDto statusDto);
// public Message<StatusDto>deleteStatus(Integer id);
// public Message<StatusDto>getAllStatus(Integer page, Integer size);
 
}
