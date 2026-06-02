package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.MealRequest;
import com.childsync.spring.dto.response.MealResponse;
import com.childsync.spring.model.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Meal mealRequestToMeal(MealRequest request);

    @Mapping(target = "userName", source = "user.name")
    MealResponse mealToMealResponse(Meal meal);

}
