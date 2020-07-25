package com.example.demo.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserDetailsException;
import com.example.demo.model.AuditDetails;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.AuditUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {

	//public Logger logger = 
	
	public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	AuditUserRepository repoAudit;	
	
	public String signup(LoginRequest loginRequest) throws UserDetailsException {
		logger.info("Begin :: signup ::" + loginRequest.getUserId());
		String status = "failed";
		try {
			UserDetails user = new UserDetails();
			user.setUserId(loginRequest.getUserId());
			user.setPassword(loginRequest.getPassword());
			user.setUserName(loginRequest.getUserName());
			user.setEmailId(loginRequest.getEmailId());
			repo.save(user);
			status = "success";
		} catch(Exception e) {
			status = "failed";
			return "failed";
		} finally {
			auditUser(loginRequest, status);
		}
		logger.info("End :: signup ::" + loginRequest.getUserId());
		return "success";
	}

	private void auditUser(LoginRequest loginRequest, String status) {
		try {
			AuditDetails audit = new AuditDetails();
			audit.setUserId(loginRequest.getUserId());
			audit.setPassword(loginRequest.getPassword());
			audit.setUserName(loginRequest.getUserName());
			audit.setEmailId(loginRequest.getEmailId());
			audit.setTimestamp(new Date());
			audit.setStatus(status);
			repoAudit.save(audit);
		} catch(Exception e) {
			logger.error("Exception while saving audit record");
		}
	}

	public UserDetails login(LoginRequest loginRequest) throws UsernameNotFoundException {

		UserDetails user = repo.findByUserIdAndPassword(loginRequest.getUserId(), loginRequest.getPassword());
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		Calendar cal = Calendar.getInstance();
		cal.set(10, Calendar.MINUTE);
		String jwt = Jwts.builder().setExpiration(cal.getTime()).setSubject(user.getUserName()).setIssuedAt(new Date()).compact();
		user.setToken(jwt);
		return user;
	}
	
	
}
