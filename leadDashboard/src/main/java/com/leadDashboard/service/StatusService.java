package com.leadDashboard.service;

import java.util.List;
import java.util.Map;

import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.StatusDto;



public interface StatusService {
 public Message	 <StatusDto>saveStatus(StatusDto statusDto);
 public Message<StatusDto>AddStatus(StatusDto statusDto);
 public Message<StatusDto>getstatusById(Integer id);
 public Message<StatusDto>updateStatus(StatusDto statusDto);
 public Message<StatusDto>deleteStatus(Integer id);
 public Map<String, Object>getAllStatus();
 
}
