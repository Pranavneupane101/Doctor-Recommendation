package com.doctor.base.core.service.User;

import com.doctor.base.application.GeoHash.GeoHashGeneratorPort;

import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.*;
@Singleton
class DoctorAlgoritham {
    private static final int NUMBER_OF_DOCTORS=10;
    @Inject
    GetDoctors getdoctors;
    @Inject
    GeoHashGeneratorPort geoHashGenerator;


    public Set<DoctorLocation> getDoctorNarrowList(String experties, String hashCode5, String hashCode6) {

        Set<DoctorLocation> doctorList =getdoctors.getDoctorNarrowList(experties, hashCode5, hashCode6);

        if(doctorList.size()>=NUMBER_OF_DOCTORS) {
            return  doctorList;
        }else{

            List<String> neighbours = geoHashGenerator.Neighbours(experties,hashCode6);
            for (String neighbour : neighbours) {
                System.out.println(neighbour);
                doctorList.addAll(getdoctors.getDoctorNarrowList(experties,hashCode5,neighbour));

            }

        }
        return doctorList;

    }
    public Set<DoctorLocation> getDoctorBroadList(String experties, String hashCode5) {

        Set<DoctorLocation> doctorList = new LinkedHashSet<>();
        doctorList =getdoctors.getDoctorBroadList(experties,hashCode5);

        if(doctorList.size()>=NUMBER_OF_DOCTORS) {
            return  doctorList;
        }else{

            List<String> neighbours = geoHashGenerator.Neighbours(experties,hashCode5);
            for (String neighbour : neighbours) {
                System.out.println(neighbour);
                doctorList.addAll(getdoctors.getDoctorNarrowList(experties,hashCode5,neighbour));

            }

        }
        return doctorList;

    }

}