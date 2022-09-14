package com.stti.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.stti.user.model.User;
import com.stti.user.openapi.model.UserDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
    
}
