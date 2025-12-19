package com.doctor.base.core.service.User;


import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;

import com.doctor.base.core.models.DoctorLocation;
import com.doctor.base.core.models.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.stream.Collectors;
@Singleton
  class DoctorRanking {
    @Inject
    DoctorRepositoryPort doctorRepositoryPort;
    public Map<DoctorLocation,Double> getRanking(User user, List<DoctorLocation> doctors){

        Map<DoctorLocation,Double> doctorLocations=new HashMap<>();

        for(DoctorLocation doctorLocation:doctors){
            doctorLocations.put(doctorLocation,  CalulateDistance.haversine(
                    doctorLocation.getLatitude(),
                    doctorLocation.getLongitude(),
                    user.getLatitude(),
                    user.getLongitude()
            ));
        }


        return doctorLocations.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
        ));


    }

}
