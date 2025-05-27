package com.leadDashboard.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
public class TargetDto {
	
	private int id;
	private int totalTargetMonthly;
	private int totalConvertedLead;
	private int totalAddedLead;
	private int uid;
	private String month;
	private String year;

}
