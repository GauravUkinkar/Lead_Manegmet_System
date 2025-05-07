package com.leadDashboard.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.leadDashboard.service.CustomUserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigration {

	private final JwtFilte jwtFilter;

	private final CustomUserServiceImpl userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable)
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        .authorizeHttpRequests(registry -> registry
	            .requestMatchers(
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/swagger-resources/**",
	                "/webjars/**"
	            ).permitAll()
	            .requestMatchers("/superadmin/**","/status/**","/lead/**").permitAll()
	            .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
	            .requestMatchers("/User/**").hasAnyAuthority("USER")
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider())
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	        .addFilterBefore(new CorsFilter(corsConfigurationSource()), ChannelProcessingFilter.class);

	    return http.build();
	}



	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*")); // ✅ Explicitly specify allowed origin
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // ✅ Explicitly allow "Authorization"
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}



	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authonticationprovider = new DaoAuthenticationProvider();
		authonticationprovider.setUserDetailsService(userDetailsService.userDetailsService());
		authonticationprovider.setPasswordEncoder(passwordEncoder());
		return authonticationprovider;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 

}
