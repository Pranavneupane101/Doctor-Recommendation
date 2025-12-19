package com.doctor.base.core.models;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Doctor {
    private String doctorId;
    private String doctorDesignation;
    private String doctorName;
    private Experties experties;
    private String experience;
    private String fee;
    private String location;



    public enum Experties {
        GENERAL_PHYSICIAN,
        CARDIOLOGIST,
        DERMATOLOGIST,
        NEUROLOGIST,
        ORTHOPEDIC,
        PEDIATRICIAN,
        PSYCHIATRIST,
        GYNECOLOGIST,
        OPHTHALMOLOGIST,
        ENT_SPECIALIST,
        DENTIST,
        ONCOLOGIST,
        RADIOLOGIST,
        UROLOGIST,
        ENDOCRINOLOGIST
    }

    public String getDoctorDesignation() {
        return doctorDesignation;
    }

    public void setDoctorDesignation(String doctorDesignation) {
        this.doctorDesignation = doctorDesignation;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Experties getExperties() {
        return experties;
    }

    public void setExperties(Experties experties) {
        this.experties = experties;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
