package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.SleepRequest;
import com.childsync.spring.dto.response.SleepResponse;
import com.childsync.spring.model.Sleep;

@Mapper(componentModel = "spring")
public interface SleepMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Sleep sleepRequestToSleep(SleepRequest request);

    @Mapping(target = "userName", source = "user.name")
    SleepResponse sleepToSleepResponse(Sleep sleep);
    
}
