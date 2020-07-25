package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="users_details.test")
public class UserDetails {
	
	public UserDetails() {
		super();
	}
	@Id
	public String id;
	
	public String userId;
	@JsonIgnore
	public String password;
	public String userName;
	public String emailId;
	public String token;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public UserDetails(String userId, String password, String userName, String emailId, String token) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.emailId = emailId;
		this.token = token;
	}
	
	

}
