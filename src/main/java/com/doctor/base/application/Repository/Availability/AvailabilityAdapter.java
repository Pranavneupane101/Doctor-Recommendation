package com.doctor.base.application.Repository.Availability;

import com.doctor.base.core.models.Availability;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
  class AvailabilityAdapter implements AvailablityPort {
   @Inject
   AvailabilityDao availabilityDao;
    @Override
    public boolean AddAvailability(Availability availability) {
        return availabilityDao.AddAvailability(AvailabilityMapper.toEntity(availability));
    }

    @Override
    public boolean UpdateAvailability(Availability availability) {
         return availabilityDao.UpdateAvailability(AvailabilityMapper.toEntity(availability));
    }

    @Override
    public boolean DeleteAvailability(String availability_id, String experties) {
        return false;
    }
}
