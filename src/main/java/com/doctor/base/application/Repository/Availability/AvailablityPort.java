package com.doctor.base.application.Repository.Availability;

import com.doctor.base.core.models.Availability;

public interface AvailablityPort {
    boolean AddAvailability(Availability availability);
    boolean UpdateAvailability(Availability availability);
    boolean DeleteAvailability(String availability_id, String experties);
}
