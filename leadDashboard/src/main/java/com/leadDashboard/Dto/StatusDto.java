package com.leadDashboard.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain=true)
public class StatusDto {
	private int id;
	private String status;

}
