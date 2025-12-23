package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.Doctor;

public interface  DoctorService {

    public boolean AddDoctor(Doctor doctor);
    public  boolean UpdateDoctor(Doctor doctor);
    public Doctor findDoctor(String doctor_id );
    public boolean DeleteDoctor(String doctor_id);


}
