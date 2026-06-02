package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.MealRequest;
import com.childsync.spring.dto.response.MealResponse;
import com.childsync.spring.mapper.MealMapper;
import com.childsync.spring.model.Meal;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.MealRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class MealService {

    private final MealRepository mealRepo;
    private final MealMapper mealMapper;
    private final UserRepository userRepo;

    public MealService(MealRepository mealRepo, MealMapper mealMapper, UserRepository userRepo) {

        this.mealRepo = mealRepo;
        this.mealMapper = mealMapper;
        this.userRepo = userRepo;

    }

    public MealResponse getById(Integer id) {

        MealResponse response = mealMapper.mealToMealResponse(mealRepo.findById(id)
                                                                      .orElseThrow());

        return response;

    }

    public List<MealResponse> getAll() {

        return mealRepo.findAll()
                       .stream()
                       .map(mealMapper::mealToMealResponse)
                       .toList();

    }

    @Transactional
    public MealResponse create(MealRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        Meal meal = mealMapper.mealRequestToMeal(request);
        meal.setUser(user);
        mealRepo.save(meal);

        MealResponse response = mealMapper.mealToMealResponse(meal);

        return response;

    }

    public String delete(Integer id) {

        if (mealRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            mealRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public MealResponse update(Integer id, MealRequest request) {

        Meal meal = mealRepo.findById(id)
                            .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        meal.setDateTime(request.dateTime());
        meal.setName(request.name());
        meal.setComment(request.comment());
        meal.setUser(user);
        mealRepo.save(meal);

        MealResponse response = mealMapper.mealToMealResponse(meal);

        return response;
        
    }
    
}
