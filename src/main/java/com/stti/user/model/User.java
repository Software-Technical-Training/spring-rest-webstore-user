package com.stti.user.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    String userid;
    
    @Indexed(unique = true)
    String username;
    String password;
    Date registrationDate;

    

}