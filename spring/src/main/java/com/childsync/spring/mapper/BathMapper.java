package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.BathRequest;
import com.childsync.spring.dto.response.BathResponse;
import com.childsync.spring.model.Bath;

@Mapper(componentModel = "spring")
public interface BathMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Bath bathRequestToBath(BathRequest request);

    @Mapping(target = "userName", source = "user.name")
    BathResponse bathToBathResponse(Bath bath);
}
