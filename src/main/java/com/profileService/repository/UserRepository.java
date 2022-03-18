package com.profileService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.profileService.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	
	
	public User findByEmail(String email);
	//public User findByUserId(String Id);

}
