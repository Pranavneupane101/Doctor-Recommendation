package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.Availability;

public interface AvailabilityService {
    boolean AddAvailability(Availability availability);
    boolean UpdateAvailability(Availability availability);

}
