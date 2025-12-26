package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Event;
import com.childsync.spring.repository.EventRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EventController {
    
    @Autowired
    EventRepository eventRepo;

    @GetMapping("/api/events")
    public List<Event> getAllEvents() {
        
        return eventRepo.findAll();

    }

    @PostMapping("/api/events")
    public Event createEvent(@RequestBody Map<String, String> body) {
        
        Event event = new Event(body.get("event_name"),
                                Timestamp.valueOf(body.get("event_datetime")),
                                Integer.parseInt(body.get("user_id")));
        
        return eventRepo.save(event);

    }
    
    @DeleteMapping("/api/events/{id}")
    public String deleteEvent(@PathVariable("id") Integer id) {

        if (eventRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            eventRepo.deleteById(id);
            return "Entry deleted";

        }

    }

    @PatchMapping("/api/events/{id}")
    public Event updateEvent(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Event event = eventRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("event_name");
        fields.add("event_datetime");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {
            
                case "event_name":
                    event.setName(body.get("event_name"));
                    break;
                case "event_datetime":
                    event.setDateTime(Timestamp.valueOf(body.get("event_datetime")));
                    break;
                case "user_id":
                    event.setUserId(Integer.parseInt(body.get("user_id")));
                    break;

            }

        }

        return eventRepo.save(event);

    }
    
}
