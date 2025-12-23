package com.doctor.base.core.service.User;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import com.doctor.base.application.Repository.Location.LocationRepositoryPort;

import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.LinkedHashSet;
import java.util.Set;
@Singleton
class GetDoctors {
    @Inject
    LocationRepositoryPort locationRepositoryPort;


    public Set<DoctorLocation> getDoctorBroadList(String experties, String geoHash5) {

        Set<DoctorLocation> doctors = new LinkedHashSet<>();
        ResultSet rs = locationRepositoryPort.getDoctorBroadLocations(experties,geoHash5);


            for (Row row : rs) {
                DoctorLocation doctor = new DoctorLocation();
                doctor.setDoctorId(row.getString("doctorid"));
                doctor.setLatitude(row.getDouble("latitude"));
                doctor.setLongitude(row.getDouble("longitude"));
                doctor.setExperties(row.getString("experties"));
                doctor.setHashcode5(row.getString("geohash5"));
                doctor.setHashcode6(row.getString("geohash6"));
                doctors.add(doctor);
            }

            return doctors;
    }

    public Set<DoctorLocation> getDoctorNarrowList(String experties,String geoHash5,String geoHash6) {

        Set<DoctorLocation> doctors = new LinkedHashSet<>();
        ResultSet rs = locationRepositoryPort.getDoctorNarrowLocations(experties,geoHash5,geoHash6);
        for (Row row : rs) {
                DoctorLocation doctor = new DoctorLocation();
                doctor.setDoctorId(row.getString("doctorid"));
                doctor.setLatitude(row.getDouble("latitude"));
                doctor.setLongitude(row.getDouble("longitude"));
                doctor.setExperties(row.getString("experties"));
                doctor.setHashcode5(row.getString("geohash5"));
                doctor.setHashcode6(row.getString("geohash6"));

                doctors.add(doctor);

        }

        return doctors;
    }
}