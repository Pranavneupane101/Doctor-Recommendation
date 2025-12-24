package com.doctor.base.core.service.Doctor;

import com.doctor.base.core.models.Availability;

import java.time.LocalDate;
import java.util.Optional;

public interface AvailabilityService {
    boolean AddAvailability(Availability availability);
    Optional<Availability> UpdateAvailability(Availability availability);
    Optional<Availability> getAvailability(String doctorId,LocalDate date);

}
