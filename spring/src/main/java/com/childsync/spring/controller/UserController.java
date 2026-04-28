package com.childsync.spring.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable("id") Integer id) {

        return userRepo.findById(id).get();
    
    }

    @PostMapping("/api/users")
    public User createUser(@RequestBody Map<String, String> body) {
        
        User user = new User(body.get("name"), body.get("email"));
        
        return userRepo.save(user);

    }

    @DeleteMapping("api/users/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {

        if (userRepo.findById(id).equals(Optional.empty())) { 

            return "Entry not found";

        } else {

            userRepo.deleteById(id);
            return "Entry deleted";

        }

    }
    
    @PatchMapping("/api/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
 
        User user = userRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("name");
        fields.add("email");

        for (String key : body.keySet()) {

            switch(key) {

                case "name":
                    user.setName(body.get("name"));
                    break;
                case "user_email":
                    user.setEmail(body.get("email"));
                    break;
            
            }

        }

        return userRepo.save(user);

    }
    
}
