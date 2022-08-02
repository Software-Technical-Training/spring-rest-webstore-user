package com.stti.user.controller;

import java.util.Date;
import java.util.List;

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
import com.stti.user.service.UserService;

@RestController
@RequestMapping("/stti")
public class UserController {
    
    @Autowired
    UserService userService;


    @GetMapping("users/{name}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("name") String name){
        return ResponseEntity.of(userService.getUserByUsername(name));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        user.setRegistrationDate(new Date());
        User newUser = userService.addUser(user);
        if(newUser != null)
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

}
