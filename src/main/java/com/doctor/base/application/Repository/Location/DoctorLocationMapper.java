package com.doctor.base.application.Repository.Location;

import com.doctor.base.core.models.DoctorLocation;

  class DoctorLocationMapper {
    public static LocationEntity toEntity(DoctorLocation doctorLocation) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setDoctorId(doctorLocation.getDoctorId());
        locationEntity.setExperties(doctorLocation.getExperties());
        locationEntity.setLatitude(doctorLocation.getLatitude());
        locationEntity.setLongitude(doctorLocation.getLongitude());
        locationEntity.setHashcode6(doctorLocation.getHashcode6());
        locationEntity.setHashcode5(doctorLocation.getHashcode5());
        return locationEntity;
    }

    public static DoctorLocation fromEntity(LocationEntity locationEntity) {
        DoctorLocation doctorLocation = new DoctorLocation();
        doctorLocation.setDoctorId(locationEntity.getDoctorId());
        doctorLocation.setExperties(locationEntity.getExperties());
        doctorLocation.setLatitude(locationEntity.getLatitude());
        doctorLocation.setLongitude(locationEntity.getLongitude());
        doctorLocation.setHashcode6(locationEntity.getHashcode6());
        locationEntity.setHashcode5(locationEntity.getHashcode5());
        return doctorLocation;
    }
}
