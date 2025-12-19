package com.doctor.base.core.service.User;



import com.doctor.base.application.Controller.DoctorDistanceResponseDTO;
import com.doctor.base.application.GeoHash.GeoHashGeneratorPort;
import com.doctor.base.application.Repository.PersonalInfo.DoctorRepositoryPort;
import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.models.DoctorLocation;
import com.doctor.base.core.models.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import java.util.*;
import java.util.stream.Collectors;


@Singleton
  class UserServiceImpl implements UserService{

    @Inject
    DoctorAlgoritham algorithem;
    @Inject
    GeoHashGeneratorPort geoHashGeneratorPort;
    @Inject
    DoctorRanking doctorRanking;
    @Inject
    DoctorRepositoryPort doctorRepositoryPort2;

    @Override
    public List<DoctorDistanceResponseDTO> getDoctorList(User user) {
        String geohash6=geoHashGeneratorPort.generateGeoHash(user.getLatitude(), user.getLongitude(),6);
        String geohash5=geohash6.substring(0,5);

         Set<DoctorLocation>doctors= algorithem.getDoctorNarrowList(user.getDoctorType(),geohash5,geohash6);
         if(doctors.size()<10){
               doctors.addAll(algorithem.getDoctorBroadList(user.getDoctorType(),geohash5));
         }

         Map<DoctorLocation,Double> sortedDoctorsLocation=doctorRanking.getRanking(user,doctors.stream().toList());


         Map<Doctor,Double>sortedDoctor=sortedDoctorsLocation.entrySet().stream().map(entry->Map.entry(
                 doctorRepositoryPort2.FindDoctor(entry.getKey().getDoctorId()
                         ,entry.getKey().getExperties())
                 ,entry.getValue())).collect(Collectors.toMap(
                  Map.Entry::getKey,
                  Map.Entry::getValue,
                  (a, b) -> a,
                  LinkedHashMap::new
          ));
        System.out.println(sortedDoctor);
          List<DoctorDistanceResponseDTO> response =new ArrayList<>();

          for(Doctor doctor : sortedDoctor.keySet()){
              DoctorDistanceResponseDTO responseDTO=new DoctorDistanceResponseDTO();
              responseDTO.setDoctor(
                      doctor
              );
              responseDTO.setDistance(sortedDoctor.get(doctor));
              response.add(responseDTO);
          }
          return response;

    }

}
