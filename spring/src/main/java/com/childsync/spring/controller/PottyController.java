package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Potty;
import com.childsync.spring.repository.PottyRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PottyController {
    
    @Autowired
    PottyRepository pottyRepo;

    @GetMapping("/api/potty")
    public List<Potty> getAllPotty() {
        
        return pottyRepo.findAll();

    }

    @PostMapping("/api/potty")
    public Potty createPotty(@RequestBody Map<String, String> body) {

        Potty potty = new Potty(body.get("potty_description"),
                                Timestamp.valueOf(body.get("potty_datetime")),
                                Integer.parseInt(body.get("user_id")));
        
        return pottyRepo.save(potty);

    }

    @DeleteMapping("/api/potty/{id}")
    public String deletePotty(@PathVariable("id") Integer id) {

        if (pottyRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            pottyRepo.deleteById(id);
            return "Entry deleted";

        }

    }

    @PatchMapping("/api/potty/{id}")
    public Potty updatePotty(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Potty potty = pottyRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("potty_description");
        fields.add("potty_datetime");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {

                case "potty_description":
                    potty.setDescription(body.get("potty_description"));
                    break;
                case "potty_datetime":
                    potty.setDateTime(Timestamp.valueOf(body.get("potty_datetime")));
                    break;
                case "user_id":
                    potty.setUserId(Integer.parseInt(body.get("user_id")));
                    break;

            }

        }

        return pottyRepo.save(potty);

    }
    
}
