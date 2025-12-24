package com.doctor.base.core.service.User;


import com.doctor.base.core.models.DoctorLocation;
import com.doctor.base.core.service.Doctor.AvailabilityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
@Singleton
  class CheckAvailability {

    @Inject
    AvailabilityService availabilityService;

    public  Set<DoctorLocation>checkDoctorsForAvailability(Set<DoctorLocation>  doctors){
        return doctors.stream().filter(doctor-> availabilityService
                        .getAvailability(doctor.getDoctorId(), LocalDate.now()).isPresent())
                        .collect(Collectors.toSet());
    }
}
