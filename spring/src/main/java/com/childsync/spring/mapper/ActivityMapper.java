package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import com.childsync.spring.dto.request.ActivityRequest;
import com.childsync.spring.dto.response.ActivityResponse;
import com.childsync.spring.model.Activity;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    
    Activity activityRequestToActivity(ActivityRequest request);

    ActivityResponse activityToActivityResponse(Activity activity);

}
