package com.doctor.base.application.Repository.PersonalInfo;

import com.doctor.base.core.models.Doctor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class DoctorAdaptor implements DoctorRepositoryPort {
    @Inject
    DoctorDao doctorDao;

    @Override
    public boolean AddDoctor(Doctor doctor) {
      return doctorDao.addDoctor(
              DoctorMapper.toEntity(doctor)
      );

    }

    @Override
    public boolean UpdateDoctor(Doctor doctor) {
        return doctorDao.updateDoctor(DoctorMapper.toEntity(doctor));

    }

    @Override
    public Doctor FindDoctor(String doctor_id) {
        DoctorEntity entity=doctorDao.getDoctorById(doctor_id);
        if(entity==null){
            return null;
        }
        return DoctorMapper.fromEntity(entity);
    }

    @Override
    public boolean DeleteDoctor(String doctorId) {
        return doctorDao.deleteDoctor(doctorId);
    }
}
