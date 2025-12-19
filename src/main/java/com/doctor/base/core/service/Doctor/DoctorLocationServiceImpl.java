package com.doctor.base.core.service.Doctor;


import com.doctor.base.application.GeoHash.GeoHashGeneratorPort;
import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;
import com.doctor.base.application.Repository.Location.LocationRepositoryPort;

import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DoctorLocationServiceImpl implements DoctorLocationService {
    @Inject
    LocationRepositoryPort locationRepositoryPort;
    @Inject
    DoctorRepositoryPort doctorRepositoryPort;
    @Inject
    GeoHashGeneratorPort geoHashGeneratorPort;


    @Override
    public boolean AddLocation(DoctorLocation location) {
        Doctor doctor=doctorRepositoryPort.FindDoctor(location.getDoctorId(),location.getExperties());
        if(doctor==null){
            return false;
        }
        location.setHashcode6(geoHashGeneratorPort.generateGeoHash(location.getLatitude(), location.getLongitude(),6));
        location.setHashcode5(location.getHashcode6().substring(0,5));
        return locationRepositoryPort.AddLocation(location);

    }

    @Override
    public boolean UpdateLocation(DoctorLocation location) {
        return locationRepositoryPort.UpdateLocation(location);
    }
}
