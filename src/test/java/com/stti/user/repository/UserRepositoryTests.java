package com.stti.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

@DataMongoTest
@Import(UserPopulatorConfiguration.class)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepo;    

    @Test
    void testFindAll(){
        assertEquals(2, userRepo.findAll().size());
    }

}
