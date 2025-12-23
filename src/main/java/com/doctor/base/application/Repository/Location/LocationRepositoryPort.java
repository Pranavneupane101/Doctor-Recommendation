package com.doctor.base.application.Repository.Location;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.doctor.base.core.models.DoctorLocation;




public interface LocationRepositoryPort {
    boolean AddLocation(DoctorLocation location);
    boolean UpdateLocation(DoctorLocation location);
    boolean DeleteLocation();
    ResultSet getDoctorNarrowLocations(String experties, String geoHash5,String geoHash6);
    ResultSet getDoctorBroadLocations(String experties, String geoHash5);
    boolean DeleteLocation(String geohash5,
                                    String experties,
                                    String geohash6,
                                    String doctorId);
}


