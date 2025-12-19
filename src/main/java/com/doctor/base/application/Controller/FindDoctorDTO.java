package com.doctor.base.application.Controller;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class FindDoctorDTO {
    String doctor_id;
    String experties;

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
        this.experties = experties;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

}
