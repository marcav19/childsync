package com.childsync.spring.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.childsync.spring.model.Medicine;
import com.childsync.spring.repository.MedicineRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MedicineController {
    
    @Autowired
    MedicineRepository medicineRepo;

    @GetMapping("/api/medicine")
    public List<Medicine> getAllMedicine() {
        
        return medicineRepo.findAll();

    }

    @PostMapping("/api/medicine")
    public Medicine createMedicine(@RequestBody Map<String, String> body) {
        
        Medicine medicine = new Medicine(body.get("medicine_name"),
                                        Timestamp.valueOf(body.get("medicine_datetime")),
                                        body.get("medicine_dosage"),
                                        Integer.parseInt(body.get("user_id")));

        return medicineRepo.save(medicine);

    }

    @DeleteMapping("/api/medicine/{id}")
    public String deleteMedicine(@PathVariable("id") Integer id) {

        if (medicineRepo.findById(id).equals(Optional.empty())) {

            return "Entry not found";

        } else {

            medicineRepo.deleteById(id);
            return "Entry deleted";

        }

    }
    
    @PatchMapping("/api/medicine/{id}")
    public Medicine updateMedicine(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {

        Medicine medicine = medicineRepo.findById(id).get();

        Set<String> fields = new HashSet<String>();
        fields.add("medicine_name");
        fields.add("medicine_datetime");
        fields.add("medicine_dosage");
        fields.add("user_id");

        for (String key : body.keySet()) {

            switch(key) {

                case "medicine_name":
                    medicine.setName(body.get("medicine_name"));
                    break;
                case "medicine_datetime":
                    medicine.setDateTime(Timestamp.valueOf(body.get("medicine_datetime")));
                    break;
                case "medicine_dosage":
                    medicine.setDosage(body.get("medicine_dosage"));
                    break;
                case "user_id":
                    medicine.setUserId(Integer.parseInt(body.get("user_id")));
                    break;

            }

        }

        return medicineRepo.save(medicine);

    }

}
