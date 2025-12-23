package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.DoctorLocation;

public interface DoctorLocationService {

        boolean AddLocation(DoctorLocation location);
        boolean UpdateLocation(DoctorLocation location);
        boolean DeleteLocation(String geoHash6,String experties, String geoHash5,String doctor_id);

}
