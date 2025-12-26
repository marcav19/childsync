package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Sleep;
import com.childsync.spring.repository.SleepRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SleepController {
    
    @Autowired
    SleepRepository sleepRepo;

    @GetMapping("/api/sleep")
    public List<Sleep> getAllSleep() {

        return sleepRepo.findAll();

    }
    
    @PostMapping("/api/sleep")
    public Sleep createSleep(@RequestBody Map<String, String> body) {
        
        Sleep sleep = new Sleep(Timestamp.valueOf(body.get("sleep_start")),
                                Timestamp.valueOf(body.get("sleep_end")),
                                Integer.parseInt((body.get("user_id"))));
        
        return sleepRepo.save(sleep);

    }
    
    @DeleteMapping("/api/sleep/{id}")
    public String deleteSleep(@PathVariable("id") Integer id) {

        if (sleepRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            sleepRepo.deleteById(id);
            return "Entry deleted";

        }

    }

    @PatchMapping("/api/sleep/{id}")
    public Sleep updateSleep(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
 
        Sleep sleep = sleepRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("sleep_start");
        fields.add("sleep_end");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {

                case "sleep_start":
                    sleep.setStart(Timestamp.valueOf(body.get("sleep_start")));
                    break;
                case "sleep_end":
                    sleep.setEnd(Timestamp.valueOf(body.get("sleep_end")));
                    break;
                case "user_id":
                    sleep.setUserId(Integer.parseInt((body.get("user_id"))));
                    break;
            
            }

        }

        return sleepRepo.save(sleep);

    }

}
