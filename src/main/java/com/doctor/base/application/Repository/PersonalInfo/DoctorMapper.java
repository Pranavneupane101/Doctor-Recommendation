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
        doctorEntity.setExperties(doctor.getExperties());
        doctorEntity.setLatitude(doctor.getLatitude());
        doctorEntity.setLongitude(doctor.getLongitude());
        doctorEntity.setPhone_no(doctor.getPhone_no());

        return doctorEntity;
    }

    public static Doctor fromEntity(DoctorEntity doctorEntity) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorEntity.getDoctorId());
        doctor.setDoctorDesignation(doctorEntity.getDoctorDesignation());
        doctor.setDoctorName(doctorEntity.getDoctorName());
        doctor.setExperience(doctorEntity.getExperience());
        doctor.setFee(doctorEntity.getFee());
        doctor.setExperties(doctorEntity.getExperties());
        doctor.setLatitude(doctorEntity.getLatitude());
        doctor.setLongitude(doctorEntity.getLongitude());
        doctor.setPhone_no(doctorEntity.getPhone_no());
        return doctor;
    }
}
