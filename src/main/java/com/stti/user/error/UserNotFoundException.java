package com.stti.user.error;

public class UserNotFoundException extends RuntimeException {

    String username;

    public UserNotFoundException(String user){
        this.username = user;
    }
    
    public String getUsername(){
        return username;
    }
}
