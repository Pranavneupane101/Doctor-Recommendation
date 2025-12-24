package com.doctor.base.application.Repository.PersonalInfo;

import com.doctor.base.core.models.Doctor;

import java.util.Optional;

public interface DoctorRepositoryPort {
    void AddDoctor(Doctor doctor);
    Optional<Doctor> UpdateDoctor(Doctor doctor);
    Optional<Doctor> FindDoctor(String doctor_id);
    boolean DeleteDoctor(String doctor_id);
}
