package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.DoctorLocation;

import java.util.Optional;

public interface DoctorLocationService {

        void AddLocation(DoctorLocation location);
       Optional<DoctorLocation> UpdateLocation(DoctorLocation location);
        boolean DeleteLocation(String geoHash6,String experties, String geoHash5,String doctor_id);

}
