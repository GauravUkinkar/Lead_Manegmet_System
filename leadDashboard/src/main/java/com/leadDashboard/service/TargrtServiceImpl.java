package com.leadDashboard.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leadDashboard.Dto.TargetDto;
import com.leadDashboard.mapper.TargetMapper;
import com.leadDashboard.model.Target;
import com.leadDashboard.model.User;
import com.leadDashboard.repository.TargetRepo;
import com.leadDashboard.repository.UserRepository;
import com.leadDashboard.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TargrtServiceImpl implements TargetService {

private final TargetRepo targetrepo;
private final UserRepository userrepo;
private final TargetMapper mapper;
	@Override
	public Map<String, Object> AddTarget(TargetDto targetDto) {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {
	    	Target model=null;
	    	TargetDto dto=null;
	    	User user=userrepo.getById(targetDto.getUid());
	    	if(user==null) {
	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
	            responseMap.put("data", Collections.emptyList());
	            return responseMap;
	    	}
	    	model=mapper.TargetDtoToTarget(targetDto);
	    	targetrepo.save(model);
	    	dto=mapper.TargettoTargetDto(model);
	    	
    		responseMap.put("Httpstatus", HttpStatus.OK);
            responseMap.put("message", Constants.TARGET_ADDED_SUCCESSFULLY);
            responseMap.put("data", dto);
            return responseMap;	
	    }catch (Exception e) {
			 responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
	         responseMap.put("message", "Something went wrong: " + e.getMessage());
	         responseMap.put("data", Collections.emptyList());
	         return responseMap;
	    }
	}

	@Override
	public Map<String, Object> getAllTargetByuid(int uid) {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {		
             List <Target> target=targetrepo.findAllTargetByuid(uid);
              if(target==null) {
  	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
  	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
  	            responseMap.put("data", Collections.emptyList());
  	            return responseMap;
  	    	}
              List<TargetDto> leadDtos = target.stream()
		                .map(mapper::TargettoTargetDto)
		                .collect(Collectors.toList());

		        responseMap.put("Httpstatus", HttpStatus.OK);
		        responseMap.put("message", Constants.TARGETS_FOUND);
		        responseMap.put("data", leadDtos);
		        return responseMap;

		    } catch (Exception e) {
		        responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
		        responseMap.put("message", "Something went wrong: " + e.getMessage());
		        responseMap.put("data", Collections.emptyList());
		        return responseMap;
		    }
	    	
	}

	@Override
	public Map<String, Object> getAllTYarget() {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {		
             List <Target> target=targetrepo.findAll();
              if(target==null) {
  	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
  	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
  	            responseMap.put("data", Collections.emptyList());
  	            return responseMap;
  	    	}
              List<TargetDto> leadDtos = target.stream()
		                .map(mapper::TargettoTargetDto)
		                .collect(Collectors.toList());

		        responseMap.put("Httpstatus", HttpStatus.OK);
		        responseMap.put("message", Constants.TARGETS_FOUND);
		        responseMap.put("data", leadDtos);
		        return responseMap;

		    } catch (Exception e) {
		        responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
		        responseMap.put("message", "Something went wrong: " + e.getMessage());
		        responseMap.put("data", Collections.emptyList());
		        return responseMap;
		    }
	    	
	}

	@Override
	public Map<String, Object> updateTarget(TargetDto targetDto) {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {	
	    	 
	    	 Target target=targetrepo.getById(targetDto.getId());
             if(target==null) {
   	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
   	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
   	            responseMap.put("data", Collections.emptyList());
   	            return responseMap;
   	    	}
              target.setMonth(targetDto.getMonth());
              target.setTotalAddedLead(targetDto.getTotalAddedLead());
              target.setTotalConvertedLead(targetDto.getTotalConvertedLead());
              target.setTotalTargetMonthly(targetDto.getTotalTargetMonthly());
              target.setYear(targetDto.getYear());
              targetrepo.save(target);
             TargetDto dto=mapper.TargettoTargetDto(target);
             responseMap.put("Httpstatus", HttpStatus.OK);
		        responseMap.put("message", Constants.TARGETS_FOUND);
		        responseMap.put("data", dto);
		        return responseMap;
	}catch (Exception e) {
        responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
        responseMap.put("message", "Something went wrong: " + e.getMessage());
        responseMap.put("data", Collections.emptyList());
        return responseMap;
    }

	}
	@Override
	public Map<String, Object> getAllTargetBymonthAndYear(String month, String year) {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {
	    	
	    	List<Target>target=targetrepo.findBymonthAndYear(month,year);
            
	        if(target==null) {
   	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
   	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
   	            responseMap.put("data", Collections.emptyList());
   	            return responseMap;
   	    	}
	        List<TargetDto> leadDtos = target.stream()
	                .map(mapper::TargettoTargetDto)
	                .collect(Collectors.toList());

	        responseMap.put("Httpstatus", HttpStatus.OK);
	        responseMap.put("message", Constants.TARGETS_FOUND);
	        responseMap.put("data", leadDtos);
	        return responseMap;

	    } catch (Exception e) {
	        responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
	        responseMap.put("message", "Something went wrong: " + e.getMessage());
	        responseMap.put("data", Collections.emptyList());
	        return responseMap;
	    }
	}

	@Override
	public Map<String, Object> getAllTargetBymonthAndYearAnduid(String month, String year, int uid) {
		Map<String, Object> responseMap = new LinkedHashMap<>();
	    try {		
	    	List<Target> target=targetrepo.findBymonthAndYearAndUid(month,year,uid);
	    	if(target==null) {
   	    		responseMap.put("Httpstatus", HttpStatus.NOT_FOUND);
   	            responseMap.put("message", Constants.NO_RECORDS_FOUND);
   	            responseMap.put("data", Collections.emptyList());
   	            return responseMap;
   	    	}
	    	 List<TargetDto> leadDtos = target.stream()
		                .map(mapper::TargettoTargetDto)
		                .collect(Collectors.toList());
	    	 responseMap.put("Httpstatus", HttpStatus.OK);
		        responseMap.put("message", Constants.TARGETS_FOUND);
		        responseMap.put("data", leadDtos);
		        return responseMap;

		    } catch (Exception e) {
		        responseMap.put("Httpstatus", HttpStatus.INTERNAL_SERVER_ERROR);
		        responseMap.put("message", "Something went wrong: " + e.getMessage());
		        responseMap.put("data", Collections.emptyList());
		        return responseMap;
		    }
	    }
	}


