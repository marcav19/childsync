package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.MedicineRequest;
import com.childsync.spring.dto.response.MedicineResponse;
import com.childsync.spring.mapper.MedicineMapper;
import com.childsync.spring.model.Medicine;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.MedicineRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicineService {
    
    private final MedicineRepository medicineRepo;
    private final MedicineMapper medicineMapper;
    private final UserRepository userRepo;

    public MedicineService(MedicineRepository medicineRepo, MedicineMapper medicineMapper, UserRepository userRepo) {

        this.medicineRepo = medicineRepo;
        this.medicineMapper = medicineMapper;
        this.userRepo = userRepo;

    }

    public MedicineResponse getById(Integer id) {

        MedicineResponse response = medicineMapper.medicineToMedicineResponse(medicineRepo.findById(id)
                                                                                          .orElseThrow());

        return response;

    }

    public List<MedicineResponse> getAll() {

        return medicineRepo.findAll()
                           .stream()
                           .map(medicineMapper::medicineToMedicineResponse)
                           .toList();

    }

    @Transactional
    public MedicineResponse create(MedicineRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        Medicine medicine = medicineMapper.medicineRequestToMedicine(request);
        medicine.setUser(user);
        medicineRepo.save(medicine);

        MedicineResponse response = medicineMapper.medicineToMedicineResponse(medicine);

        return response;

    }

    public String delete(Integer id) {

        if (medicineRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            medicineRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public MedicineResponse update(Integer id, MedicineRequest request) {

        Medicine medicine = medicineRepo.findById(id)
                                        .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        medicine.setDateTime(request.dateTime());
        medicine.setName(request.name());
        medicine.setDosage(request.dosage());
        medicine.setUser(user);
        medicineRepo.save(medicine);

        MedicineResponse response = medicineMapper.medicineToMedicineResponse(medicine);

        return response;
        
    }
}
