package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Appointment;
import com.childsync.spring.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AppointmentController {
    
    @Autowired
    AppointmentRepository appointmentRepo;

    @GetMapping("/api/appointments")
    public List<Appointment> getAllAppointments() {
        
        return appointmentRepo.findAll();

    }

    @PostMapping("/api/appointments")
    public Appointment createAppointment(@RequestBody Map<String, String> body) {
        
        Appointment appointment = new Appointment(body.get("appointment_reason"),
                                                  Timestamp.valueOf(body.get("appointment_datetime")),
                                                  body.get("appointment_result"),
                                                  Integer.parseInt(body.get("user_id")));

        return appointmentRepo.save(appointment);

    }
    
    @DeleteMapping("/api/appointments/{id}")
    public String deleteAppointment(@PathVariable("id") Integer id) {

        if (appointmentRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            appointmentRepo.deleteById(id);
            return "Entry deleted";

        }

    }

    @PatchMapping("/api/appointments/{id}")
    public Appointment updateAppointment(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Appointment appointment = appointmentRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("appointment_reason");
        fields.add("appointments_datetime");
        fields.add("appointment_result");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {

                case "appointment_reason":
                    appointment.setReason(body.get("appointment_reason"));
                    break;
                case "appointment_datetime":
                    appointment.setDateTime(Timestamp.valueOf(body.get("appointment_datetime")));
                    break;
                case "appointment_result":
                    appointment.setResult(body.get("appointment_result"));
                    break;
                case "user_id":
                    appointment.setUserId(Integer.parseInt(body.get("user_id")));
                    break;

            }

        }

        return appointmentRepo.save(appointment);

    }
    
}
