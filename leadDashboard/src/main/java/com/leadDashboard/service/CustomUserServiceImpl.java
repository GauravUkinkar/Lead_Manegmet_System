package com.leadDashboard.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leadDashboard.model.User;
import com.leadDashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements CustomUserService{

	private final UserRepository repo;

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			  @Override
		        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		            User user = repo.findByEmail(email)
		                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

		            return org.springframework.security.core.userdetails.User.builder()
		                    .username(user.getEmail())
		                    .password(user.getPassword()) // Must be encoded with BCrypt
		                    .authorities(user.getRole())   // e.g., "ROLE_ADMIN" or "ADMIN"
		                    .build();
		        }
			
		};
	}

}
