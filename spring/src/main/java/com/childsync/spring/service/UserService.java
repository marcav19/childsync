package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.UserRequest;
import com.childsync.spring.dto.response.UserResponse;
import com.childsync.spring.mapper.UserMapper;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepo, UserMapper userMapper) {

        this.userRepo = userRepo;
        this.userMapper = userMapper;

    }

    public UserResponse getById(Integer id) {

        UserResponse response = userMapper.userToUserResponse(userRepo.findById(id)
                                                                      .orElseThrow());

        return response;

    }

    public List<UserResponse> getAll() {

        return userRepo.findAll()
                       .stream()
                       .map(userMapper::userToUserResponse)
                       .toList();
                       
    }

    @Transactional
    public UserResponse create(UserRequest request) {

        User user = userMapper.userRequestToUser(request);
        userRepo.save(user);

        UserResponse response = userMapper.userToUserResponse(user);

        return response;

    }

    public String delete(Integer id) {

        if (userRepo.findById(id).isEmpty()) {
            
            return "Deletion failed";

        } else {

            userRepo.deleteById(id);
            return "Deletion successful";
        
        }

    }

    @Transactional
    public UserResponse update(Integer id, UserRequest request) {

        User user = userRepo.findById(id)
                            .orElseThrow();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        userRepo.save(user);

        UserResponse response = userMapper.userToUserResponse(user);

        return response;

    }
}
