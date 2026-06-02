package com.childsync.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.dto.request.MedicineRequest;
import com.childsync.spring.dto.response.MedicineResponse;
import com.childsync.spring.service.MedicineService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {

        this.medicineService = medicineService;

    }

    @GetMapping("/{id}")
    public MedicineResponse getMedicineById(@PathVariable("id") Integer id) {
        
        return medicineService.getById(id);

    }
    
    @GetMapping
    public List<MedicineResponse> getAllMedicine() {
        
        return medicineService.getAll();

    }

    @PostMapping
    public MedicineResponse createMedicine(@RequestBody MedicineRequest request) {
        
        return medicineService.create(request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteMedicine(@PathVariable("id") Integer id) {

        return medicineService.delete(id);

    }
    
    @PutMapping("/{id}")
    public MedicineResponse updateMedicine(@PathVariable("id") Integer id, @RequestBody MedicineRequest request) {

        return medicineService.update(id, request);

    }

}
