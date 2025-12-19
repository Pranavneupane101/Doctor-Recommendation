package com.doctor.base.core.service.Doctor;

import com.doctor.base.application.Repository.Availability.AvailablityPort;
import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;
import com.doctor.base.core.models.Doctor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class DoctorServiceImpl implements DoctorService{
    @Inject
    DoctorRepositoryPort doctorRepositoryPort;
    @Inject
    AvailablityPort  availablityPort;

    @Override
    public boolean AddDoctor(Doctor doctor) {

       return doctorRepositoryPort.AddDoctor(doctor);

    }
    @Override
    public boolean UpdateDoctor(Doctor doctor) {
        return doctorRepositoryPort.UpdateDoctor(doctor);

    }
    @Override
    public Doctor findDoctor(String doctor_id, String experties) {
        return doctorRepositoryPort.FindDoctor(doctor_id,experties);
    }


}
