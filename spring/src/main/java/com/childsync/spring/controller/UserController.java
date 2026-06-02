package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.UserRequest;
import com.childsync.spring.dto.response.UserResponse;
import com.childsync.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Integer id) {

        return userService.getById(id);
    
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userService.getAll();

    }

    @PostMapping
    public UserResponse createUser(UserRequest request) {
        
        return userService.create(request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable("id") Integer id) {

        return userService.delete(id);

    }
    
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable("id") Integer id, UserRequest request) {
 
        return userService.update(id, request);

    }
    
}
