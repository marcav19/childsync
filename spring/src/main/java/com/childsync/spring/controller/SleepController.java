package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.SleepRequest;
import com.childsync.spring.dto.response.SleepResponse;
import com.childsync.spring.service.SleepService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/sleeps")
public class SleepController {
    
    private final SleepService sleepService;

    public SleepController(SleepService sleepService) {

        this.sleepService = sleepService;

    }

    @GetMapping("/{id}")
    public SleepResponse getSleepById(@PathVariable("id") Integer id) {
        
        return sleepService.getById(id);
        
    }

    @GetMapping
    public List<SleepResponse> getAllSleep() {

        return sleepService.getAll();

    }
    
    @PostMapping
    public SleepResponse createSleep(@RequestBody SleepRequest request) {
        
        return sleepService.create(request);

    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteSleep(@PathVariable("id") Integer id) {

        return sleepService.delete(id);
        
    }

    @PutMapping("/{id}")
    public SleepResponse updateSleep(@PathVariable("id") Integer id, @RequestBody SleepRequest request) {
 
        return sleepService.update(id, request);

    }

}
