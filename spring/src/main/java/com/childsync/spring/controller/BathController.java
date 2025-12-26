package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Bath;
import com.childsync.spring.repository.BathRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BathController {
    
    @Autowired
    BathRepository bathRepo;

    @GetMapping("/api/baths")
    public List<Bath> getAllBaths() {
        
        return bathRepo.findAll();

    }

    @PostMapping("/api/baths")
    public Bath createBath(@RequestBody Map<String, String> body) {
    
        Bath bath = new Bath(Timestamp.valueOf(body.get("bath_datetime")),
                            Integer.parseInt(body.get("user_id")));
        
        return bathRepo.save(bath);
    }

    @DeleteMapping("/api/baths/{id}")
    public String deleteBath(@PathVariable("id") Integer id) {

        if (bathRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";
        
        } else {

            bathRepo.deleteById(id);
            return "Entry deleted";

        }

    }

    @PatchMapping("/api/baths/{id}")
    public Bath updateBath(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Bath bath = bathRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("bath_datetime");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {

                case "bath_datetime":
                    bath.setDateTime(Timestamp.valueOf(body.get("bath_datetime")));
                    break;
                case "user_id":
                    bath.setUserId(Integer.parseInt(body.get("user_id")));
                    break;

            }

        }

        return bathRepo.save(bath);

    }
    
}
