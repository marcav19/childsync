package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Meal;
import com.childsync.spring.repository.MealRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MealController {

    @Autowired
    MealRepository mealRepo;

    @GetMapping("/api/meals")
    public List<Meal> getAllMeals() {
        
        return mealRepo.findAll();
    
    }
    
    @PostMapping("/api/meals")
    public Meal createMeal(@RequestBody Map<String, String> body) {

        Meal meal;

        if (body.size() == 4) {

            meal = new Meal(body.get("meal_name"),
                                Timestamp.valueOf(body.get("meal_datetime")),
                                body.get("meal_comment"),
                                Integer.parseInt(body.get("user_id")));

        } else {

            meal = new Meal(body.get("meal_name"),
                                Timestamp.valueOf(body.get("meal_datetime")),
                                Integer.parseInt(body.get("user_id")));

        }

        return mealRepo.save(meal);

    }

    @DeleteMapping("/api/meals/{id}")
    public String deleteMeal(@PathVariable("id") Integer id) {

        if (mealRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            mealRepo.deleteById(id);
            return "Entry deleted";
        }

    }
    
    @PatchMapping("api/meals/{id}")
    public Meal updateMeal(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Meal meal = mealRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("meal_name");
        fields.add("meal_datetime");
        fields.add("meal_comment");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {
                case "meal_name":
                    meal.setName(body.get("meal_name"));
                    break;
                case "meal_datetime":
                    meal.setDateTime(Timestamp.valueOf(body.get("meal_datetime")));
                    break;
                case "meal_comment":
                    meal.setComment(body.get("meal_comment"));
                    break;
                case "user_id":
                    meal.setUserId(Integer.parseInt(body.get("user_id")));
                    break;
            }

        }

        return mealRepo.save(meal);

    }
    
}
