package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.AppointmentRequest;
import com.childsync.spring.dto.response.AppointmentResponse;
import com.childsync.spring.mapper.AppointmentMapper;
import com.childsync.spring.model.Appointment;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.AppointmentRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final UserRepository userRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, AppointmentMapper appointmentMapper, UserRepository userRepo) {

        this.appointmentRepo = appointmentRepo;
        this.appointmentMapper = appointmentMapper;
        this.userRepo = userRepo;

    }

    public AppointmentResponse getById(Integer id) {

        AppointmentResponse response = appointmentMapper.appointmentToAppointmentResponse(appointmentRepo.findById(id)
                                                                                                         .orElseThrow());

        return response;
    }

    public List<AppointmentResponse> getAll() {

        return appointmentRepo.findAll()
                              .stream()
                              .map(appointmentMapper::appointmentToAppointmentResponse)
                              .toList();
        
    }

    @Transactional
    public AppointmentResponse create(AppointmentRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        Appointment appointment = appointmentMapper.appointmentRequestToAppointment(request);
        appointment.setUser(user);
        appointmentRepo.save(appointment);

        AppointmentResponse response = appointmentMapper.appointmentToAppointmentResponse(appointment);

        return response;

    }

    public String delete(Integer id) {

        if (appointmentRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            appointmentRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public AppointmentResponse update(Integer id, AppointmentRequest request) {

        Appointment appointment = appointmentRepo.findById(id)
                                                 .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        appointment.setDateTime(request.dateTime());
        appointment.setReason(request.reason());
        appointment.setResult(request.result());
        appointment.setUser(user);
        appointmentRepo.save(appointment);

        AppointmentResponse response = appointmentMapper.appointmentToAppointmentResponse(appointment);
        
        return response;

    }

}
