package com.leadDashboard.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain=true)
public class EventDto {
	private int eId;
	private String attended;
	private String date;
	private String duration;
	private String time;
	private String name;
	private String location;
	private String entryFee;
	private String discription;
	private String links;
	private String comments;
	

}
