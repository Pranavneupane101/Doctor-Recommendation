package com.doctor.base.application.Repository.Location;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.doctor.base.core.models.DoctorLocation;

import javax.xml.stream.Location;
import java.util.Optional;


public interface LocationRepositoryPort {
    void AddLocation(DoctorLocation location);
    Optional<DoctorLocation > UpdateLocation(DoctorLocation location);

    ResultSet getDoctorNarrowLocations(String experties, String geoHash5,String geoHash6);
    ResultSet getDoctorBroadLocations(String experties, String geoHash5);
    boolean DeleteLocation(String geohash5,
                                    String experties,
                                    String geohash6,
                                    String doctorId);
}


