package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.PottyRequest;
import com.childsync.spring.dto.response.PottyResponse;
import com.childsync.spring.model.Potty;

@Mapper(componentModel = "spring")
public interface PottyMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Potty pottyRequestToPotty(PottyRequest request);

    @Mapping(target = "userName", source = "user.name")
    PottyResponse pottyToPottyResponse(Potty potty);
    
}
