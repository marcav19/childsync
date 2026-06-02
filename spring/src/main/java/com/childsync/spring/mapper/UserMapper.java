package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.UserRequest;
import com.childsync.spring.dto.response.UserResponse;
import com.childsync.spring.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    User userRequestToUser(UserRequest request);
    
    UserResponse userToUserResponse(User user);

}
