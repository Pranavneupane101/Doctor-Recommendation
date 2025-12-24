package com.doctor.base.core.service.Doctor;



import com.doctor.base.application.Redis.DoctorLocationRedis.DoctorLocationRedisPort;
import com.doctor.base.application.Repository.Location.LocationRepositoryPort;

import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.logging.Logger;

@Singleton
public class DoctorLocationServiceImpl implements DoctorLocationService {

    private final Logger logger= Logger.getLogger(DoctorLocationServiceImpl.class.getName());
    @Inject
    LocationRepositoryPort locationRepositoryPort;


    @Inject
    DoctorLocationRedisPort doctorLocationRedisPort;

    @Override
    public void AddLocation(DoctorLocation location) {

                 locationRepositoryPort.AddLocation(location);


                 boolean redisstatus=doctorLocationRedisPort.AddLocation(location);
                 if(redisstatus){
                      logger.info("Doctor location added in the cache");
                 }else{
                     logger.info("Doctor location could not be added in the cache");
                 }


        }

    @Override
    public Optional<DoctorLocation> UpdateLocation(DoctorLocation location) {
         Optional<DoctorLocation> updatedLocation=locationRepositoryPort.UpdateLocation(location);
        if (updatedLocation.isPresent()) {

            if (doctorLocationRedisPort.UpdateLocation(location)) {
                logger.info("Doctor location updated in the cache");
            } else {
                logger.info("Doctor location could not be updated in the cache");

            }
            return  updatedLocation;
        }
        return Optional.empty();
    }

    @Override
    public boolean DeleteLocation(String geoHash6, String experties, String geoHash5, String doctor_id) {
        return locationRepositoryPort.DeleteLocation(geoHash6,experties,geoHash5,doctor_id);

    }
}


