package com.stti.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.stti.user.model.User;
import com.stti.user.openapi.model.UserDto;
import com.stti.user.repository.UserRepository;

public class UserServiceTests {

    UserRepository repo = mock(UserRepository.class);

    
    @Test
    public void testGetAllUsers(){
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        when(repo.findAll()).thenReturn(List.of(user1,user2));

        UserService svc = new UserService(repo);
        ResponseEntity<List<UserDto>> res = svc.getAllUsers();
        assertEquals(res.getStatusCode(),HttpStatus.OK);
        assertEquals(2, res.getBody().size());

    }
}
