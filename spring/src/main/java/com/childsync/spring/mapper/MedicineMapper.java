package com.childsync.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.childsync.spring.dto.request.MedicineRequest;
import com.childsync.spring.dto.response.MedicineResponse;
import com.childsync.spring.model.Medicine;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Medicine medicineRequestToMedicine(MedicineRequest request);

    @Mapping(target = "userName", source = "user.name")
    MedicineResponse medicineToMedicineResponse(Medicine medicine);

}
