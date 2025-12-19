package com.doctor.base.application.Repository.PersonalInfo;

import com.doctor.base.core.models.Doctor;

public class DoctorMapper {

    public static DoctorEntity toEntity(Doctor doctor) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setDoctorId(doctor.getDoctorId());
        doctorEntity.setDoctorDesignation( doctor.getDoctorDesignation());
        doctorEntity.setDoctorName(doctor.getDoctorName());
        doctorEntity.setExperience(doctor.getExperience());
        doctorEntity.setFee(doctor.getFee());
        doctorEntity.setExperties(doctor.getExperties().name());
        doctorEntity.setLocation(doctor.getLocation());
        return doctorEntity;
    }
    public static Doctor fromEntity(DoctorEntity doctorEntity) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorEntity.getDoctorId());
        doctor.setDoctorDesignation(doctorEntity.getDoctorDesignation());
        doctor.setDoctorName(doctorEntity.getDoctorName());
        doctor.setExperience(doctorEntity.getExperience());
        doctor.setFee(doctorEntity.getFee());
        doctor.setExperties(Doctor.Experties.valueOf(doctorEntity.getExperties()));
        doctor.setLocation(doctorEntity.getLocation());
        return doctor;
    }
}
