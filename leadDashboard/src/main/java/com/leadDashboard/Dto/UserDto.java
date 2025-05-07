package com.leadDashboard.Dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	private int id;
	private String email;
	private String password;
	private String name;
	private String role;
	private String otp;
	public String deltedTag;
	public String token;

}
