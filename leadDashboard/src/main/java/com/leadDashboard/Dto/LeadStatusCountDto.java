package com.leadDashboard.Dto;

import lombok.Data;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@ToString
@Accessors(chain=true)
@Data
@RequiredArgsConstructor




public class LeadStatusCountDto {
	private long open;
	private long close;
	public LeadStatusCountDto(long open, long close) {
		super();
		this.open = open;
		this.close = close;
	}
	
	
	


	

	
	}
	
	


