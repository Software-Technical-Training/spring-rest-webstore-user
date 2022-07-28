package com.stti.user.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stti.user.model.User;
import com.stti.user.repository.UserRepository;

@RestController
@RequestMapping("/stti")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("users/{name}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("name") String username){
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRegistrationDate(new Date());

        return new ResponseEntity<>(userRepository.save(newUser),HttpStatus.CREATED);

    }



    

}
