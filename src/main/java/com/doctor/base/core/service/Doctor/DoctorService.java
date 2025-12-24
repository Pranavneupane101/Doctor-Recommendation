package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.Doctor;

import java.util.Optional;

public interface  DoctorService {

    public void AddDoctor(Doctor doctor);
    public Optional<Doctor> UpdateDoctor(Doctor doctor);
    public Optional<Doctor> findDoctor(String doctor_id );
    public boolean DeleteDoctor(String doctor_id);


}
