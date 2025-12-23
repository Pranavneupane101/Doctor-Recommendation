package com.doctor.base.core.service.Doctor;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.doctor.base.application.GeoHash.GeoHashGeneratorPort;

import com.doctor.base.application.Redis.DoctorRedis.DoctorRedisPort;

import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;
import com.doctor.base.core.models.Doctor;

import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


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
    public boolean AddDoctor(Doctor doctor) {

        String unique_Id=Uuids.timeBased().toString();
        doctor.setDoctorId(unique_Id);
        logger.info("Doctor_Id genertaed!");
        boolean repoStatus =doctorRepositoryPort.AddDoctor(doctor);
        if(repoStatus){
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
            logger.info(doctor.getDoctorName()+"'s data added to cach.");
        }else{
         logger.info(doctor.getDoctorName()+"'s data could not be added to cache.");
        }
        }

        return repoStatus;

    }

    @Override
    public boolean UpdateDoctor(Doctor doctor) {

        String doctorId=doctor.getDoctorId();
        Doctor oldDoctor=findDoctor(doctorId);

        String geoHash6=geoHashGeneratorPort.generateGeoHash(oldDoctor.getLatitude(),oldDoctor.getLongitude(),6);
        String geoHash5=geoHash6.substring(0,5);


        for(String experty:oldDoctor.getExperties()) {
            System.out.println(experty);
            doctorLocationService.DeleteLocation(geoHash5,experty,geoHash6, oldDoctor.getDoctorId());
        }

        boolean repoStatus=doctorRepositoryPort.UpdateDoctor(doctor);
        if(repoStatus){
              geoHash6=geoHashGeneratorPort.generateGeoHash(doctor.getLatitude(),doctor.getLongitude(),6);
              geoHash5=geoHash6.substring(0,5);
            for(String experty:doctor.getExperties()) {
                DoctorLocation doctorLocation = new DoctorLocation();
                doctorLocation.setDoctorId(doctor.getDoctorId());
                doctorLocation.setLatitude(doctor.getLatitude());
                doctorLocation.setLongitude(doctor.getLongitude());
                doctorLocation.setExperties(experty);
                doctorLocation.setHashcode5(geoHash5);
                doctorLocation.setHashcode6(geoHash6);
                doctorLocationService.UpdateLocation(doctorLocation);
            }
            boolean cachUpdateStatus=doctorRedisPort.UpdateDoctor(doctor);
            if(cachUpdateStatus){
                System.out.println("Updated in cach ");
            }else{
                System.out.println("CachUpdated ");
            }

        }

        return repoStatus;

    }
    @Override
    public Doctor findDoctor(String doctor_id ) {

        Doctor redisDoctor =doctorRedisPort.findDoctor(doctor_id);
        if (redisDoctor!=null){
            System.out.println(redisDoctor.getDoctorName()+"'s"+"data fetched from cach ");
            return redisDoctor;
        }

        Doctor repoDoctor=doctorRepositoryPort.FindDoctor(doctor_id);

        doctorRedisPort.AddDoctor(repoDoctor);
        return repoDoctor;
    }

    @Override
    public boolean DeleteDoctor(String doctor_id) {

            Doctor doctor =  findDoctor(doctor_id);

            boolean status=doctorRepositoryPort.DeleteDoctor(doctor_id);
            if(status){
                String geoHash6=geoHashGeneratorPort.generateGeoHash(doctor.getLatitude(),doctor.getLongitude(),6);
                String geoHash5=geoHash6.substring(0,5);
                System.out.println(doctor.getExperties());
                for(String experty:doctor.getExperties()) {
                    System.out.println(doctor.getExperties());
                    doctorLocationService.DeleteLocation(geoHash5,experty,geoHash6,doctor_id);
                }
                doctorRedisPort.deleteDoctor(doctor_id);
            }
            return status;
    }
}
