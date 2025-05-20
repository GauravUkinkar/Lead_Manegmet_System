package com.leadDashboard.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@ToString
@Accessors(chain=true)
@Data
@RequiredArgsConstructor




public class LeadStatusCountDto {
	private long Open;
	private long Close;
	public LeadStatusCountDto(long open, long close) {
		super();
		Open = open;
		Close = close;
	}
	
	


	

	
	}
	
	


