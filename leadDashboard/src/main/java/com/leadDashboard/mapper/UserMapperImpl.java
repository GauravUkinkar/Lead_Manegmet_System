package com.leadDashboard.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.leadDashboard.Dto.RegistrationDto;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.model.User;

@Component
public class UserMapperImpl implements UserMapper {
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	@Override
	public UserDto toUserDto(User user) {
		return new UserDto().setDeltedTag(user.getDeltedTag())
				.setEmail(user.getEmail())
				.setId(user.getId())
				.setPassword(user.getPassword())
				.setRole(user.getRole());
	}

	@Override
	public User toUser(UserDto userDto) {
		 String encryptedPassword = passwordEncoder.encode(userDto.getPassword());		
		 return new User().setDeltedTag("False")
				 .setEmail(userDto.getEmail())
				 .setPassword(encryptedPassword)
				 .setRole(userDto.getRole());
	}


	@Override
	public UserDto toUserDto(RegistrationDto request) {
		return new UserDto().setEmail(request.getEmail())
				.setPassword(request.getPassword())
				.setRole(request.getRole());
	}

}
