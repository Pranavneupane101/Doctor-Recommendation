package com.doctor.base.application.Repository.Availability;

import com.doctor.base.core.models.Availability;

import java.time.LocalDate;
import java.util.Optional;

public interface AvailablityPort {
    boolean AddAvailability(Availability availability);
    Optional<Availability> UpdateAvailability(Availability availability);
    boolean DeleteAvailability(String availability_id , LocalDate availability_date);
    Optional<Availability>getAvailability(String availability_id , LocalDate availability_date);

}
