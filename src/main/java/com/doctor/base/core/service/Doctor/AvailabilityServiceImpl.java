package com.doctor.base.core.service.Doctor;

import com.doctor.base.application.Repository.Availability.AvailablityPort;
import com.doctor.base.core.models.Availability;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AvailabilityServiceImpl implements AvailabilityService{
    @Inject
    AvailablityPort availablityPort;

    @Override
    public boolean AddAvailability(Availability availability) {

        return availablityPort.AddAvailability(availability);
    }

    @Override
    public boolean UpdateAvailability(Availability availability) {
     return  availablityPort.UpdateAvailability(availability);
    }
}
