package com.leadDashboard.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.leadDashboard.Dto.ChangePasswordDto;
import com.leadDashboard.Dto.LoginRequest;
import com.leadDashboard.Dto.Message;
import com.leadDashboard.Dto.RegistrationDto;
import com.leadDashboard.Dto.UserDto;
import com.leadDashboard.mapper.UserMapper;
import com.leadDashboard.model.User;
import com.leadDashboard.repository.UserRepository;
import com.leadDashboard.util.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {
private final UserMapper userMapper;
private final UserRepository userRepository;
private final JwtService jwtService;
private final PasswordEncoder passwordEncoder;
private final OTPGenerateService otpService;
private final EmailService emailService;
private final  Configuration config;

@Value("${spring.mail.username}") 
private String sender;
@Value("${spring.mail.password}")
private String password;

	@Override
	public Message<UserDto> registerUser(RegistrationDto request) {
		Message<UserDto> message = new Message<>();
		try {
			UserDto userDto = userMapper.toUserDto(request);
			User user = userMapper.toUser(userDto);
			userRepository.save(user);
			String token = jwtService.genrateToken(user);
			userDto.setToken(token);
			message.setStatus(HttpStatus.CREATED);
			message.setResponseMessage(Constants.SUCCESS);
			message.setData(userDto);
			log.info(Constants.SUCCESS + "  " + message.getData());
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public Message<UserDto> loginUser(LoginRequest request) {
		Message<UserDto> message = new Message<>();
		User user = null;
		UserDto userDto =null;
		 try {
		        user = userRepository.getByEmail(request.getEmail()); // Find by email only

		        if (user == null || "True".equalsIgnoreCase(user.getDeltedTag())) {
		            message.setStatus(HttpStatus.NOT_FOUND);
		            message.setResponseMessage(Constants.RECORD_NOT_FOUND);
		            log.info(Constants.RECORD_NOT_FOUND);
		            return message;
		        }

		        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
		            message.setStatus(HttpStatus.UNAUTHORIZED);
		            message.setResponseMessage("Invalid credentials");
		            log.info("Invalid credentials");
		            return message;
		        }

		        String token = jwtService.genrateToken(user);
		        userDto = userMapper.toUserDto(user);
		        userDto.setToken(token);

		        message.setStatus(HttpStatus.OK);
		        message.setResponseMessage(Constants.LOGIN_SUCCESSFULL);
		        message.setData(userDto);
		        log.info(Constants.LOGIN_SUCCESSFULL + "  " + message.getData());

		        return message;

		    } catch (Exception e) {
		        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        message.setResponseMessage(e.getMessage());
		        log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
		        return message;
		    }
		}

	@Override
	public Message<UserDto> sendOtp(String email) {
		   Message<UserDto> message = new Message<>();
	        User user= userRepository.getByEmail(email);
	        try {
	        	if (user==null|| "True".equalsIgnoreCase(user.getDeltedTag())) {
					message.setStatus(HttpStatus.NOT_FOUND);
					message.setResponseMessage(Constants.RECORD_NOT_FOUND);
					return message;
				}else {
					String otp = otpService.generateOTP(email);
					Properties props = new Properties();
					Session session = Session.getInstance(props, new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(sender, password);
						}
					});
					
					MimeMessage mailMessage = new MimeMessage(session);

					MimeMessageHelper helper = new MimeMessageHelper(mailMessage,
							MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
					Map<String, Object> model = new HashMap<>();
					model.put("request1", otp);
					model.put("user", user);
					Template t = config.getTemplate("Otp.html");
					String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//					 SimpleMailMessage mailMessage = new SimpleMailMessage();
			            helper.setFrom(sender);
			            helper.setTo(user.getEmail());
			            helper.setSubject("Forgot Password!!");
			            helper.setText(html,true);

					
					emailService.sendEmail(mailMessage);
					user.setOtp(otp);
					userRepository.save(user);
					message.setStatus(HttpStatus.OK);
					message.setResponseMessage(Constants.OTP_SENT_SUCCESSFULLY);
					return message;
				}
			} catch (Exception e) {
				message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				message.setResponseMessage(e.getMessage());
				log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
				return message;
	        }
	        
		}

	@Override
	public Message<UserDto> updatePassword(ChangePasswordDto request) {
		 Message<UserDto> message = new Message<>();
		    String encryptedPassword = passwordEncoder.encode(request.getConfirmPassword());

		    try {
		        // Fetch the user by email
		        User user = userRepository.getByEmail(request.getEmail());

		        // Check if the user exists and is not deleted
		        if (user == null || "True".equalsIgnoreCase(user.getDeltedTag())) {
		            message.setStatus(HttpStatus.NOT_FOUND);
		            message.setResponseMessage(Constants.RECORD_NOT_FOUND);
		            return message;
		        }

		        // Validate OTP if provided
		        if (user.getOtp().equals(request.getOtp()) || request.getOtp() == null) {
		            message.setStatus(HttpStatus.OK);
		            message.setResponseMessage(Constants.OTP_VERIFIED_SUCCESSFULLY);
		        } else {
		            message.setStatus(HttpStatus.BAD_REQUEST);
		            message.setResponseMessage(Constants.OTP_VERIFICATION_FAILED);
		            return message;
		        }

		        // Check if new password and confirm password match
		        if (request.getNewPassword().equals(request.getConfirmPassword())) {
		            user.setPassword(encryptedPassword);
		            userRepository.save(user);

		            message.setStatus(HttpStatus.OK);
		            message.setResponseMessage(Constants.PASSWORD_CHANGED_SUCCESSFULLY);
		        } else {
		            message.setStatus(HttpStatus.BAD_REQUEST);
		            message.setResponseMessage(Constants.PASSWORD_MISMATCH);
		        }

		        return message;

		    } catch (Exception e) {
		        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        message.setResponseMessage(e.getMessage());
		        log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
		        return message;
		    }
		}

	@Override
	public List<Message<UserDto>> getAllUsers(Integer page, Integer size) {
		List<Message<UserDto>> messages = new ArrayList<>(); 
	    try {
	    	 int pageNumber = (page == null || page <= 0) ? 0 : page - 1;

	         int pageSize = (size == null || size <= 0) ? 10 : size;

	         Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<User> users = userRepository.findAll(pageable);
	        
	        if (users == null || users.isEmpty()) { 
	            Message<UserDto> message = new Message<>();
	            message.setStatus(HttpStatus.NOT_FOUND);
	            message.setResponseMessage(Constants.RECORD_NOT_FOUND);
	            messages.add(message);
	            return messages;
	        }
	        
	        for (User user : users) { 
	            Message<UserDto> message = new Message<>();
	            
	            if ("True".equalsIgnoreCase(user.getDeltedTag())) { 
	                message.setStatus(HttpStatus.NOT_FOUND);
	                message.setResponseMessage(Constants.RECORD_NOT_FOUND);
	            } else {
	                message.setStatus(HttpStatus.OK);
	                message.setResponseMessage(Constants.USER_FOUND);
	                message.setData(userMapper.toUserDto(user)); 
	            }
	            messages.add(message); 
	        }
	        
	        return messages; 
	    } catch (Exception e) {
	        Message<UserDto> errorMessage = new Message<>();
	        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        errorMessage.setResponseMessage(e.getMessage());
	        log.error(Constants.SOMETHING_WENT_WRONG + "  " + errorMessage.getResponseMessage());
	        messages.add(errorMessage);
	        return messages;
	    }
	}

	@Override
	public Message<UserDto> deleteUser(Integer id) {
		Message<UserDto> message = new Message<>();
		try {
			User user= userRepository.getById(id);
			if (user == null || "True".equalsIgnoreCase(user.getDeltedTag())) {
			    message.setStatus(HttpStatus.NOT_FOUND);
			    message.setResponseMessage(Constants.RECORD_NOT_FOUND);
			    return message;
			}
			user.setDeltedTag("True");
			userRepository.save(user);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.USER_DELETED);
			message.setData(userMapper.toUserDto(user));
			return message;
		}catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}
	

	@Override
	public Message<UserDto> getUserById(Integer id) {
		Message<UserDto> message = new Message<>();
		try {
			User user= userRepository.getById(id);
			if (user == null || "True".equalsIgnoreCase(user.getDeltedTag())) {
			    message.setStatus(HttpStatus.NOT_FOUND);
			    message.setResponseMessage(Constants.RECORD_NOT_FOUND);
			    return message;
			}
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.USER_FOUND);
			message.setData(userMapper.toUserDto(user));
			return message;
		}catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public Message<UserDto> changePassword(ChangePasswordDto request) {
		 Message<UserDto> message = new Message<>();
		    String encryptedPassword = passwordEncoder.encode(request.getConfirmPassword());

		    try {
		        // Fetch the user by email
		        User user = userRepository.getByEmail(request.getEmail());

		        // Check if the user exists and is not deleted
		        if (user == null || "True".equalsIgnoreCase(user.getDeltedTag())) {
		            message.setStatus(HttpStatus.NOT_FOUND);
		            message.setResponseMessage(Constants.RECORD_NOT_FOUND);
		            return message;
		        }
		        // Check if new password and confirm password match
		        if (request.getNewPassword().equals(request.getConfirmPassword())) {
		            user.setPassword(encryptedPassword);
		            userRepository.save(user);

		            message.setStatus(HttpStatus.OK);
		            message.setResponseMessage(Constants.PASSWORD_CHANGED_SUCCESSFULLY);
		        } else {
		            message.setStatus(HttpStatus.BAD_REQUEST);
		            message.setResponseMessage(Constants.PASSWORD_MISMATCH);
		        }

		        return message;

		    } catch (Exception e) {
		        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        message.setResponseMessage(e.getMessage());
		        log.error(Constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
		        return message;
		    }
	}

}
