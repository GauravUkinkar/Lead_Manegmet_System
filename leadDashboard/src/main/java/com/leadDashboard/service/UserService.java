package com.leadDashboard.service;

import java.util.List;

import com.leadDashboard.Dto.ChangePasswordDto;
import com.leadDashboard.Dto.LoginRequest;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.RegistrationDto;
import com.leadDashboard.Dto.UserDto;



public interface UserService {
	public Message<UserDto>registerUser(RegistrationDto request);
	public Message<UserDto>loginUser(LoginRequest request);
	public Message<UserDto>sendOtp(String email);
	public Message<UserDto>updatePassword(ChangePasswordDto request);
    public List<Message<UserDto>>getAllUsers(Integer page, Integer size);
//	public Message<UserDto>updateUser(UserDto request);
	public Message<UserDto>deleteUser(Integer id);
	public Message<UserDto>getUserById(Integer id);
	
}
