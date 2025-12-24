package com.doctor.base.application.Repository.Availability;

import com.doctor.base.core.models.Availability;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Optional;

@Singleton
  class AvailabilityAdapter implements AvailablityPort {
   @Inject
   AvailabilityDao availabilityDao;
    @Override
    public boolean AddAvailability(Availability availability) {
        return availabilityDao.AddAvailability(AvailabilityMapper.toEntity(availability));
    }

    @Override
    public Optional<Availability> UpdateAvailability(Availability availability) {

          Optional<AvailabilityEntity> optional=availabilityDao.UpdateAvailability(AvailabilityMapper.toEntity(availability)) ;
          return optional.map(AvailabilityMapper::fromEntity);
    }

    @Override
    public boolean DeleteAvailability(String availability_id, LocalDate availability_date) {
        return availabilityDao.DeleteAvailability(availability_id,availability_date);
    }

    @Override
    public Optional<Availability> getAvailability(String availability_id, LocalDate availability_date) {
        Optional<AvailabilityEntity> optional=availabilityDao.getAvailability(availability_id,availability_date);
        return optional.map(AvailabilityMapper::fromEntity);
    }


}
