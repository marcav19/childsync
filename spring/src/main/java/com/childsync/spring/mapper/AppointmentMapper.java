package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.AppointmentRequest;
import com.childsync.spring.dto.response.AppointmentResponse;
import com.childsync.spring.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Appointment appointmentRequestToAppointment(AppointmentRequest request);

    @Mapping(target = "userName", source = "user.name")
    AppointmentResponse appointmentToAppointmentResponse(Appointment appointment);

}
