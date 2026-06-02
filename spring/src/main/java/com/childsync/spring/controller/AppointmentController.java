package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.AppointmentRequest;
import com.childsync.spring.dto.response.AppointmentResponse;
import com.childsync.spring.service.AppointmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {

        this.appointmentService = appointmentService;

    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointment(@PathVariable("id") Integer id) {
        
        return appointmentService.getById(id);

    }
    
    @GetMapping
    public List<AppointmentResponse> getAllAppointments() {
        
        return appointmentService.getAll();

    }

    @PostMapping
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest request) {
        
        return appointmentService.create(request);

    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteAppointment(@PathVariable("id") Integer id) {

        return appointmentService.delete(id);

    }

    @PutMapping("/{id}")
    public AppointmentResponse updateAppointment(@PathVariable("id") Integer id, @RequestBody AppointmentRequest request) {

        return appointmentService.update(id, request);

    }
    
}
