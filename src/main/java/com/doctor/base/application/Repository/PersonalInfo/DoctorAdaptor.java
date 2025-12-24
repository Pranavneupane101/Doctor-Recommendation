package com.doctor.base.application.Repository.PersonalInfo;

import com.doctor.base.core.models.Doctor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
class DoctorAdaptor implements DoctorRepositoryPort {
    @Inject
    DoctorDao doctorDao;

    @Override
    public void AddDoctor(Doctor doctor) {
       doctorDao.addDoctor(
              DoctorMapper.toEntity(doctor)
      );

    }

    @Override
    public Optional<Doctor> UpdateDoctor(Doctor doctor) {
       Optional <DoctorEntity> updatedDoctor= doctorDao.updateDoctor(DoctorMapper.toEntity(doctor));
        return updatedDoctor.map(DoctorMapper::fromEntity);

    }

    @Override
    public Optional<Doctor> FindDoctor(String doctor_id) {
        Optional<DoctorEntity> entity=doctorDao.getDoctorById(doctor_id);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return entity.map(DoctorMapper::fromEntity);
    }

    @Override
    public boolean DeleteDoctor(String doctorId) {

        return doctorDao.deleteDoctor(doctorId);
    }
}
