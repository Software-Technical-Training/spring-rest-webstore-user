package com.stti.user.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    String userid;
    
    String username;
    String password;
    Date registrationDate;

    

}