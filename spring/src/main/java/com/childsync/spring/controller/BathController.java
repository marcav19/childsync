package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.BathRequest;
import com.childsync.spring.dto.response.BathResponse;
import com.childsync.spring.service.BathService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/baths")
public class BathController {
    
    private final BathService bathService;

    public BathController(BathService bathService) {

        this.bathService = bathService;

    }

    @GetMapping("/{id}")
    public BathResponse getBathById(@PathVariable("id") Integer id) {
        
        return bathService.getById(id);

    }
    
    @GetMapping
    public List<BathResponse> getAllBaths() {
        
        return bathService.getAll();

    }

    @PostMapping
    public BathResponse createBath(@RequestBody BathRequest request) {
    
        return bathService.create(request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteBath(@PathVariable("id") Integer id) {

        return bathService.delete(id);

    }

    @PutMapping("/{id}")
    public BathResponse updateBath(@PathVariable("id") Integer id, @RequestBody BathRequest request) {

        return bathService.update(id, request);

    }
    
}
