package com.stti.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stti.user.model.User;

public interface UserRepository extends MongoRepository<User,String> {
    
    @Query("{ 'username': ?0 }")
    User findUserByUsername(String name);
}
