package com.doctor.base.core.service.Doctor;



import com.doctor.base.application.Redis.DoctorLocationRedis.DoctorLocationRedisPort;
import com.doctor.base.application.Repository.Location.LocationRepositoryPort;

import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DoctorLocationServiceImpl implements DoctorLocationService {
    @Inject
    LocationRepositoryPort locationRepositoryPort;


    @Inject
    DoctorLocationRedisPort doctorLocationRedisPort;

    @Override
    public boolean AddLocation(DoctorLocation location) {

        boolean repoStatus=locationRepositoryPort.AddLocation(location);
             if(repoStatus){

                 boolean redisstatus=doctorLocationRedisPort.AddLocation(location);
                 if(redisstatus){
                     System.out.println("Added to the doctor location to redis");
                 }else{
                     System.out.println("Failed to add to doctor location to redis");
                 }
             }
             return repoStatus;
        }

    @Override
    public boolean UpdateLocation(DoctorLocation location) {
        boolean repoStatus = locationRepositoryPort.UpdateLocation(location);
        if (repoStatus) {

            if (doctorLocationRedisPort.UpdateLocation(location)) {
                System.out.println("Updated to the doctor location cach");
            } else {
                System.out.println("Failed to update to doctor location cach");
            }
        }
        return repoStatus;
    }

    @Override
    public boolean DeleteLocation(String geoHash6, String experties, String geoHash5, String doctor_id) {
        return locationRepositoryPort.DeleteLocation(geoHash6,experties,geoHash5,doctor_id);

    }
}


