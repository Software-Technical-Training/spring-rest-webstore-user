package com.stti.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stti.user.model.User;
import com.stti.user.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String name){
        return userRepository.findUserByUsername(name);
    }

    public User addUser(User user){
        try {
            return userRepository.save(user);            
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
}
