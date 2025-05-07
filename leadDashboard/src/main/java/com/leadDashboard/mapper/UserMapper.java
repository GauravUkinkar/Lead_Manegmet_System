package com.leadDashboard.mapper;

import com.leadDashboard.Dto.RegistrationDto;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.model.User;

public interface UserMapper {
public UserDto toUserDto(User user);
public User toUser(UserDto userDto);
public UserDto toUserDto(RegistrationDto request);

}
