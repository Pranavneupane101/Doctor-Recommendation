package com.doctor.base.application.Repository.Location;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.doctor.base.application.Connection.CqlConnection;
import jakarta.inject.Singleton;
import java.util.UUID;
@Singleton

  class DoctorLocationDao {
    private final CqlConnection connection;
    public DoctorLocationDao(CqlConnection connection) {
        this.connection = connection;
    }

    public boolean AddDoctorLocation(LocationEntity doctor){
        CqlSession session=connection.getConnection();
        String insertQuery =
                "INSERT INTO doctor_location (" +
                        "experties, geohash5,geohash6, doctorId, latitude, longitude" +
                        ") VALUES (?, ?, ?, ?, ?,?)";

        return session.execute(session.prepare(insertQuery).bind(
                doctor.getExperties(),
                doctor.getHashcode5(),
                doctor.getHashcode6(),
                UUID.fromString(doctor.getDoctorId()),
                doctor.getLatitude(),
                doctor.getLongitude()
        )).wasApplied();
    }

    public boolean UpdateDoctorLocation(LocationEntity doctor){

        String updateQuery =
                "UPDATE doctor_location SET " +
                        "latitude = ?, longitude = ? " +
                        "WHERE experties = ? AND geohash5 = ? AND geohash6=? AND doctorId = ?";
        CqlSession session=connection.getConnection();

        return session.execute(session.prepare(updateQuery).bind(
                doctor.getLatitude(),
                doctor.getLongitude(),
                doctor.getExperties(),
                doctor.getHashcode5(),
                doctor.getHashcode6(),
                doctor.getDoctorId(),
                UUID.fromString(doctor.getDoctorId())

        )).wasApplied();

    }


    public  ResultSet  getDoctorLocationListBroad (String experties, String geohash5){
        CqlSession session=connection.getConnection();
        String query =
                "SELECT * FROM doctor_location WHERE experties = ? AND geohash5 = ?";
        return  session.execute(session.prepare(query)
                .bind(experties,geohash5));
    }

    public  ResultSet  getDoctorLocationListNarrow(String experties, String geohash5,String geohash6){
        CqlSession session=connection.getConnection();
        String query =
                "SELECT * FROM doctor_location WHERE experties = ? AND geohash5 = ? AND geohash6 = ?";
        return  session.execute(session.prepare(query)
                .bind(experties,geohash5,geohash6));
    }

}
