package com.doctor.base.application.Repository.Availability;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

  class AvailabilityEntity {
    String doctorId;
    LocalDate date;
    LocalTime availablefrom;
    LocalTime availableTo;
    List<LocalTime> appointments;


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getAvailablefrom() {
        return availablefrom;
    }

    public void setAvailablefrom(LocalTime availablefrom) {
        this.availablefrom = availablefrom;
    }

    public LocalTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalTime availableTo) {
        this.availableTo = availableTo;
    }

    public List<LocalTime> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<LocalTime> appointments) {
        this.appointments = appointments;
    }


}
