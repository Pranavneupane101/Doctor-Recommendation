package com.doctor.base.application.Controller;

import com.doctor.base.core.models.Doctor;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class DoctorDistanceResponseDTO {
    private Doctor doctor;
    private Double Distance;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }
}
