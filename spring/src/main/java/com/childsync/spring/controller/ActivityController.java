package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.ActivityRequest;
import com.childsync.spring.dto.response.ActivityResponse;
import com.childsync.spring.service.ActivityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {

        this.activityService = activityService;

    }

    @GetMapping("/{id}")
    public ActivityResponse getActivityById(@PathVariable("id") Integer id) {
        
        return activityService.getById(id);
        
    }

    @GetMapping
    public List<ActivityResponse> getAllActivites() {
        
        return activityService.getAll();

    }

    @PostMapping
    public ActivityResponse createActivity(@RequestBody ActivityRequest request) {
        
        return activityService.create(request);

    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteActivity(@PathVariable("id") Integer id) {

        return activityService.delete(id);

    }

    @PutMapping("/{id}")
    public ActivityResponse updateActivity(@PathVariable("id") Integer id, @RequestBody ActivityRequest request) {

        return activityService.update(id, request);

    }
    
}
