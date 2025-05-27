package com.leadDashboard.mapper;

import com.leadDashboard.Dto.TargetDto;
import com.leadDashboard.model.Target;

public interface TargetMapper {
	public TargetDto TargettoTargetDto(Target target);
	public Target TargetDtoToTarget(TargetDto targetDto);

}
