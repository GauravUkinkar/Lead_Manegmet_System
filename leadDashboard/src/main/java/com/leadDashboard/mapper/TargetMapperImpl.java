package com.leadDashboard.mapper;

import org.springframework.stereotype.Component;

import com.leadDashboard.Dto.TargetDto;
import com.leadDashboard.model.Target;

@Component
public class TargetMapperImpl implements TargetMapper {

	@Override
	public TargetDto TargettoTargetDto(Target target) {
		return new TargetDto().
				setId(target.getId()).
				setMonth(target.getMonth()).
				setTotalAddedLead(target.getTotalAddedLead()).
				setTotalConvertedLead(target.getTotalConvertedLead()).
			    setTotalTargetMonthly(target.getTotalTargetMonthly()).
			    setUid(target.getUid()).
			    setYear(target.getYear());
	}

	@Override
	public Target TargetDtoToTarget(TargetDto targetDto) {
	  return new Target().setMonth(targetDto.getMonth()).
			  setTotalAddedLead(targetDto.getTotalAddedLead()).
			  setTotalConvertedLead(targetDto.getTotalConvertedLead()).
			  setTotalTargetMonthly(targetDto.getTotalTargetMonthly()).
			  setUid(targetDto.getUid()).
			  setYear(targetDto.getYear());
	  
	}

}
