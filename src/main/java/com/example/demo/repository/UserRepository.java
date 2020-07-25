package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.UserDetails;


public interface UserRepository extends MongoRepository<UserDetails, String>{

	public UserDetails save(UserDetails user);
	
	public UserDetails findByUserIdAndPassword(String userId, String password) throws UsernameNotFoundException;
	
}
