package com.example.demo.service;

import com.example.demo.exception.UserDetailsException;
import com.example.demo.request.LoginRequest;

public interface UserService {

	
	public String signup(LoginRequest user) throws UserDetailsException;
	
	
}
