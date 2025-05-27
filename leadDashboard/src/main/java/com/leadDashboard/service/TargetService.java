package com.leadDashboard.service;

import java.util.Map;

import com.leadDashboard.Dto.TargetDto;

public interface TargetService {
	
	 public Map<String, Object>AddTarget(TargetDto targetDto);
	 public Map<String, Object>getAllTargetByuid(int uid);
	 public Map<String, Object>getAllTYarget();
	 public Map<String, Object> updateTarget(TargetDto targetDto);
	 public Map<String, Object>getAllTargetBymonthAndYear(String month,String year);
	 public Map<String, Object>getAllTargetBymonthAndYearAnduid(String month,String year,int uid);
	

}
