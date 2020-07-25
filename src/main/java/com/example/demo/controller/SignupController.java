package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserDetailsException;
import com.example.demo.model.UserDetails;
import com.example.demo.request.LoginRequest;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/")
public class SignupController {

	public static Logger logger = LoggerFactory.getLogger(SignupController.class);
	
	@Autowired
	UserServiceImpl service;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody LoginRequest loginRequest) {
		String status = "failed";
		try {
			 status = service.signup(loginRequest);
		} catch(UserDetailsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
			//return "error.html";
		}
		return ResponseEntity.ok().body(status);
		//return "login.html";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		UserDetails user = new UserDetails();
		logger.info("Begin login :"+loginRequest.getUserId());
		try {
			 user = service.login(loginRequest);
			 
		} catch(UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		//	return "errorLogin.html";
		}
		logger.info("End login :"+user.toString());
		return ResponseEntity.ok().body(user);
		//return "success.html";
	}	
}
