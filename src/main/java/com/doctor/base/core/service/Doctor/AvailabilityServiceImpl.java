package com.doctor.base.core.service.Doctor;

import com.doctor.base.application.Repository.Availability.AvailablityPort;
import com.doctor.base.core.models.Availability;
import com.doctor.base.core.models.Doctor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Optional;

@Singleton
public class AvailabilityServiceImpl implements AvailabilityService{
    @Inject
    AvailablityPort availablityPort;
    @Inject
    DoctorService doctorService;

    @Override
    public boolean AddAvailability(Availability availability) {

        Optional<Doctor> doctor= doctorService.findDoctor(availability.getDoctorId());
        if (doctor.isEmpty()){
            return false;
        }
        return availablityPort.AddAvailability(availability);
    }

    @Override
    public Optional<Availability> UpdateAvailability(Availability availability) {
           Optional<Availability> updatedAvailability =availablityPort.getAvailability(availability.getDoctorId(), availability.getDate());
           if(updatedAvailability.isPresent()){
               return availablityPort.UpdateAvailability(availability);
           }else {
               return Optional.empty();
           }
    }

    @Override
    public Optional<Availability> getAvailability(String doctorId,LocalDate date) {
        return availablityPort.getAvailability(doctorId,date);
    }


}
