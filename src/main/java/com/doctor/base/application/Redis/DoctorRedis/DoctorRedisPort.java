package com.doctor.base.application.Redis.DoctorRedis;

import com.doctor.base.core.models.Doctor;



public interface DoctorRedisPort {
    public boolean AddDoctor(Doctor doctor);
    public  boolean UpdateDoctor(Doctor doctor);
    public  Doctor  findDoctor(String doctor_id);
    public boolean deleteDoctor(String doctor_id);
}
