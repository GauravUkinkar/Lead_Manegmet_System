package com.leadDashboard.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class RegistrationDto {
	private String email;
	private String password;
	private String name;
	private String role;
}
