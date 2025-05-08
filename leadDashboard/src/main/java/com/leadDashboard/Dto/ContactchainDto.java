package com.leadDashboard.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class ContactchainDto {
	private int cid;
	private String contactDate;
	private String method;
	private String response;
	private String followUpDate;
	private String feedback;
	private int lid;
}
