package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.MealRequest;
import com.childsync.spring.dto.response.MealResponse;
import com.childsync.spring.service.MealService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {

        this.mealService = mealService;

    }

    @GetMapping("/{id}")
    public MealResponse getMealById(@PathVariable("id") Integer id) {
        
        return mealService.getById(id);

    }
    
    @GetMapping
    public List<MealResponse> getAllMeals() {
        
        return mealService.getAll();
    
    }
    
    @PostMapping
    public MealResponse createMeal(@RequestBody MealRequest request) {

        return mealService.create(request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteMeal(@PathVariable("id") Integer id) {

        return mealService.delete(id);

    }
    
    @PutMapping("/{id}")
    public MealResponse updateMeal(@PathVariable("id") Integer id, @RequestBody MealRequest request) {

        return mealService.update(id, request);

    }
    
}
