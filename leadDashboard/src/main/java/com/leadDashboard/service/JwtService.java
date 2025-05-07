package com.leadDashboard.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.leadDashboard.model.User;


public interface JwtService {
	 String extractUsername(String token);
	 String genrateToken(User userdetails);
	 public boolean isTokenValid(String token,UserDetails userdetails);
	
}
