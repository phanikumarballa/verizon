package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.AuditDetails;


public interface AuditUserRepository extends MongoRepository<AuditDetails, String>{

	public AuditDetails save(AuditDetails user);
	
	
}
