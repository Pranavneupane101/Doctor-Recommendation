package com.doctor.base.application.Repository.Availability;

import com.doctor.base.core.models.Availability;

 class AvailabilityMapper {

    public static AvailabilityEntity toEntity(Availability availability){
        AvailabilityEntity availabilityEntity = new AvailabilityEntity();
        availabilityEntity.setDoctorId(availability.getDoctorId());
        availabilityEntity.setAvailableTo(availability.getAvailableTo());
        availabilityEntity.setAvailablefrom(availability.getAvailablefrom());
        availabilityEntity.setDate(availability.getDate());
        availabilityEntity.setAppointments(availability.getAppointments());


        return availabilityEntity;
    }

    public static Availability fromEntity(AvailabilityEntity availabilityEntity){
        Availability availability = new Availability();
        availability.setDoctorId(availabilityEntity.getDoctorId());
        availability.setAvailablefrom(availabilityEntity.getAvailablefrom());
        availability.setAvailableTo(availabilityEntity.getAvailableTo());
        availability.setDate(availabilityEntity.getDate());
        availability.setAppointments(availabilityEntity.getAppointments());

        return availability;
    }
}
