package com.stti.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.stti.user.mapper.UserMapper;
import com.stti.user.model.User;
import com.stti.user.openapi.model.UserDto;
import com.stti.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    
    @Test
    public void testGetAllUsers(){
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));

        ResponseEntity<List<UserDto>> res = userService.getAllUsers();
        assertEquals(res.getStatusCode(),HttpStatus.OK);
        assertEquals(2, res.getBody().size());

    }

    @Test
    public void testCreateUser(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password");

        Mockito.when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        ResponseEntity<UserDto> res = userService.createUser(userMapper.toUserDto(user1));
        assertNotNull(res);
        assertEquals(res.getStatusCode(),HttpStatus.CREATED);
        User createdUser = userMapper.toUser(res.getBody());
        assertNotNull(createdUser);
        assertEquals(createdUser.getUsername(), "user1");
        assertEquals(createdUser.getPassword(), "password");

    }
}
