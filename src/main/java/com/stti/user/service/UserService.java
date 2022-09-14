package com.stti.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stti.user.mapper.UserMapper;
import com.stti.user.model.User;
import com.stti.user.openapi.api.UserApiDelegate;
import com.stti.user.openapi.model.UserDto;
import com.stti.user.repository.UserRepository;

@Service
public class UserService implements UserApiDelegate{
    
    @Autowired
    UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(userMapper.toUserDto(user));            
        }

        return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        Date dt = new Date();
        user.setRegistrationDate(dt);
        user.setLastUpdated(dt);
        System.out.println(user.toString());
        User newUser = userRepository.save(user);
        
        return new ResponseEntity<UserDto>(userMapper.toUserDto(newUser),HttpStatus.CREATED);
    }


    /**
     * DELETE /user/{username} : Delete user
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted (required)
     * @return Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     * @see UserApi#deleteUser
     */
    public ResponseEntity<Void> deleteUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username); 
        if(user.isPresent()){
            userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * GET /user/{username} : Get user by user name
     * 
     *
     * @param username The name that needs to be fetched. Use user1 for testing.  (required)
     * @return successful operation (status code 200)
     *         or Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     * @see UserApi#getUserByName
     */
    public ResponseEntity<UserDto> getUserByName(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()){
            return new ResponseEntity<>(userMapper.toUserDto(user.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * PUT /user/{username} : Update user
     * This can only be done by the logged in user.
     *
     * @param username name that need to be updated (required)
     * @param userDto Update an existent user in the store (optional)
     * @return successful operation (status code 200)
     * @see UserApi#updateUser
     */
    public ResponseEntity<UserDto> updateUser(String username,
        UserDto userDto) {

        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setPassword(userDto.getPassword());
            updatedUser.setFirstname(userDto.getFirstname());
            updatedUser.setLastname(userDto.getLastname());
            updatedUser.setPhone(userDto.getPhone());
            updatedUser.setEmail(userDto.getEmail());
            updatedUser.setLastUpdated(new Date());
            userRepository.save(updatedUser);
            return new ResponseEntity<UserDto>(userMapper.toUserDto(updatedUser),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
