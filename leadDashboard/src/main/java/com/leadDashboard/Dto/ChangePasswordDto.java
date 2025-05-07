package com.leadDashboard.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangePasswordDto {

	private String email;
	private String otp;
	private String newPassword;
	private String confirmPassword;

}
