package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.ActivityRequest;
import com.childsync.spring.dto.response.ActivityResponse;
import com.childsync.spring.model.Activity;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Activity activityRequestToActivity(ActivityRequest request);

    @Mapping(target = "userName", source = "user.name")
    ActivityResponse activityToActivityResponse(Activity activity);

}
