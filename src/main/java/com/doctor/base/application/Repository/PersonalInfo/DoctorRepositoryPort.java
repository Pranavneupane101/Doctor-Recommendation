package com.doctor.base.application.Repository.PersonalInfo;

import com.doctor.base.core.models.Doctor;

public interface DoctorRepositoryPort {
    boolean AddDoctor(Doctor doctor);
    boolean UpdateDoctor(Doctor doctor);
    Doctor FindDoctor(String doctor_id,String experties);
    boolean DeleteDoctor(String doctor_id);
}
