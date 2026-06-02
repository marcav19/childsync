package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.PottyRequest;
import com.childsync.spring.dto.response.PottyResponse;
import com.childsync.spring.service.PottyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/potties")
public class PottyController {
    
    private final PottyService pottyService;

    public PottyController(PottyService pottyService) {

        this.pottyService = pottyService;

    }

    @GetMapping("/{id}")
    public PottyResponse getPottyById(@PathVariable("id") Integer id) {
        
        return pottyService.getById(id);

    }
    
    @GetMapping
    public List<PottyResponse> getAllPotty() {
        
        return pottyService.getAll();

    }

    @PostMapping
    public PottyResponse createPotty(@RequestBody PottyRequest request) {

        return pottyService.create(request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deletePotty(@PathVariable("id") Integer id) {

        return pottyService.delete(id);

    }

    @PutMapping("/{id}")
    public PottyResponse updatePotty(@PathVariable("id") Integer id, @RequestBody PottyRequest request) {

        return pottyService.update(id, request);

    }
    
}
