package com.doctor.base.core.service.Doctor;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.doctor.base.application.GeoHash.GeoHashGeneratorPort;

import com.doctor.base.application.Redis.DoctorRedis.DoctorRedisPort;

import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;
import com.doctor.base.core.models.Doctor;

import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Singleton
class DoctorServiceImpl implements DoctorService{
       private static  final Logger logger=Logger.getLogger(DoctorServiceImpl.class.getName());


    @Inject
    DoctorRepositoryPort doctorRepositoryPort;
    @Inject
    DoctorRedisPort doctorRedisPort;
     @Inject
     DoctorLocationService doctorLocationService;
     @Inject
    GeoHashGeneratorPort geoHashGeneratorPort;


    @Override
    public void AddDoctor(Doctor doctor) {

        String unique_Id=Uuids.timeBased().toString();
        doctor.setDoctorId(unique_Id);
        logger.info("Doctor_Id genertaed!");
        doctorRepositoryPort.AddDoctor(doctor);

        Set<String> experties= doctor.getExperties();

        logger.info("Doctors Locations GeoHash Calculated!");
        String geoHash6=geoHashGeneratorPort.generateGeoHash(doctor.getLatitude(),doctor.getLongitude(),6);
        String geoHash5=geoHash6.substring(0,5);

        for(String experty:experties){
            DoctorLocation doctorLocation=new DoctorLocation();
            doctorLocation.setDoctorId(unique_Id);
            doctorLocation.setLatitude(doctor.getLatitude());
            doctorLocation.setLongitude(doctor.getLongitude());
            doctorLocation.setHashcode5(geoHash5);
            doctorLocation.setHashcode6(geoHash6);
            doctorLocation.setExperties(experty);
            doctorLocationService.AddLocation(doctorLocation);
        }
        logger.info("Doctors information added to the location table successfully!");
        boolean status=doctorRedisPort.AddDoctor(doctor);
        if(status){
            logger.info(doctor.getDoctorName()+"'s data added to Doctor cach.");
        }else{
         logger.info(doctor.getDoctorName()+"'s data could not be added to Doctor cache.");
        }




    }

    @Override
    public Optional<Doctor> UpdateDoctor(Doctor doctor) {

        String doctorId=doctor.getDoctorId();
        Optional<Doctor> oldDoctor=findDoctor(doctorId);

        if(oldDoctor.isPresent()) {
            String geoHash6 = geoHashGeneratorPort.generateGeoHash(oldDoctor.get(
            ).getLatitude(), oldDoctor.get().getLongitude(), 6);
            String geoHash5 = geoHash6.substring(0, 5);


            for (String experty : oldDoctor.get().getExperties()) {
                doctorLocationService.DeleteLocation(geoHash5, experty, geoHash6, oldDoctor.get().getDoctorId());
            }

            Optional<Doctor> updatedDoctor = doctorRepositoryPort.UpdateDoctor(doctor);
            if (updatedDoctor.isPresent()) {
                geoHash6 = geoHashGeneratorPort.generateGeoHash(doctor.getLatitude(), doctor.getLongitude(), 6);
                geoHash5 = geoHash6.substring(0, 5);
                for (String experty : doctor.getExperties()) {
                    DoctorLocation doctorLocation = new DoctorLocation();
                    doctorLocation.setDoctorId(doctor.getDoctorId());
                    doctorLocation.setLatitude(doctor.getLatitude());
                    doctorLocation.setLongitude(doctor.getLongitude());
                    doctorLocation.setExperties(experty);
                    doctorLocation.setHashcode5(geoHash5);
                    doctorLocation.setHashcode6(geoHash6);
                    doctorLocationService.UpdateLocation(doctorLocation);
                }
                boolean cachUpdateStatus = doctorRedisPort.UpdateDoctor(doctor);
                if (cachUpdateStatus) {
                    logger.info("Updated the doctor information  in the doctor cache ");
                    System.out.println("Updated in doctor");
                } else {
                    logger.info("Couldn't updated the doctor information  in the cache ");
                }
                return updatedDoctor;
            }
            return Optional.empty();
        }
        return Optional.empty();

    }
    @Override
    public Optional<Doctor> findDoctor(String doctor_id ) {

        Doctor redisDoctor =doctorRedisPort.findDoctor(doctor_id);
        if (redisDoctor!=null){
            System.out.println(redisDoctor.getDoctorName()+"'s"+"data fetched from cach ");
            return Optional.of(redisDoctor);
        }

        Optional<Doctor> repoDoctor=doctorRepositoryPort.FindDoctor(doctor_id);
        if(repoDoctor.isPresent()){
            doctorRedisPort.AddDoctor(repoDoctor.get());
            return repoDoctor;
        }else{
            return Optional.empty();
        }



    }

    @Override
    public boolean DeleteDoctor(String doctor_id) {

            Optional<Doctor> doctor =  findDoctor(doctor_id);
            if(doctor.isPresent()){

            boolean status=doctorRepositoryPort.DeleteDoctor(doctor_id);
            if(status){
                String geoHash6=geoHashGeneratorPort.generateGeoHash(doctor.get().getLatitude(),doctor.get().getLongitude(),6);
                String geoHash5=geoHash6.substring(0,5);
                System.out.println(doctor.get().getExperties());
                for(String experty:doctor.get().getExperties()) {

                    doctorLocationService.DeleteLocation(geoHash5,experty,geoHash6,doctor_id);
                }
                doctorRedisPort.deleteDoctor(doctor_id);
            }
            return status;

    }
            return false;
}
}
