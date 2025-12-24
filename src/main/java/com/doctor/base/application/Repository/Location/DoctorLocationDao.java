package com.doctor.base.application.Repository.Location;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.doctor.base.application.Connection.CqlConnection;
import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.UUID;
@Singleton

  class DoctorLocationDao {
    private final CqlConnection connection;
    public DoctorLocationDao(CqlConnection connection) {
        this.connection = connection;
    }

    public void AddDoctorLocation(LocationEntity doctor){
        CqlSession session=connection.getConnection();
        String insertQuery =
                "INSERT INTO doctor_location (" +
                        "geohash5, experties, geohash6, doctorid, latitude, longitude" +
                        ") VALUES (?, ?, ?, ?, ?, ?)";

          session.execute(session.prepare(insertQuery).bind(
                doctor.getHashcode5(),
                doctor.getExperties(),
                doctor.getHashcode6(),
                doctor.getDoctorId(),
                doctor.getLatitude(),
                doctor.getLongitude()
        ));
    }

    public Optional<LocationEntity> UpdateDoctorLocation(LocationEntity doctor){

        CqlSession session = connection.getConnection();

        String updateQuery =
                "UPDATE doctor_location SET " +
                        "latitude = ?, longitude = ? " +
                        "WHERE geohash5 = ? AND experties = ? AND geohash6 = ? AND doctorid = ?";

        session.execute(session.prepare(updateQuery).bind(
                doctor.getLatitude(),
                doctor.getLongitude(),
                doctor.getHashcode5(),
                doctor.getExperties(),
                doctor.getHashcode6(),
                 doctor.getDoctorId()
        )) ;

          return getDoctorLocation(doctor.getExperties(),doctor.getHashcode5(),doctor.getHashcode6(),doctor.getDoctorId());

    }
    public boolean deleteDoctorLocation(String geohash5,
                                     String experties,
                                     String geohash6,
                                     String doctorId) {

        CqlSession session = connection.getConnection();

        String query =
                "DELETE FROM doctor_location " +
                        "WHERE geohash5 = ? AND experties = ? AND geohash6 = ? AND doctorid = ?";

        return session.execute(session.prepare(query)
                .bind(geohash5, experties, geohash6, doctorId)).wasApplied();
    }


    public ResultSet getDoctorLocationListBroad(String experties, String geohash5) {
        CqlSession session = connection.getConnection();

        String query =
                "SELECT * FROM doctor_location " +
                        "WHERE geohash5 = ? AND experties = ?";

        return session.execute(session.prepare(query)
                .bind(geohash5, experties));
    }

    public ResultSet getDoctorLocationListNarrow(
            String experties, String geohash5, String geohash6) {

        CqlSession session = connection.getConnection();

        String query =
                "SELECT * FROM doctor_location " +
                        "WHERE geohash5 = ? AND experties = ? AND geohash6 = ?";

        return session.execute(session.prepare(query)
                .bind(geohash5, experties, geohash6));
    }

    public Optional<LocationEntity> getDoctorLocation(
            String experties, String geohash5, String geohash6, String doctorId) {

        CqlSession session = connection.getConnection();

        String query =
                "SELECT * FROM doctor_location " +
                        "WHERE geohash5 = ? AND experties = ? AND geohash6 = ? AND  doctorid = ?";



         Row rw= session.execute(session.prepare(query)
                .bind(geohash5, experties, geohash6,doctorId)).one();
         if(rw!=null) {
             LocationEntity entity = new LocationEntity();
             entity.setDoctorId(rw.getString("doctorId"));
             entity.setLatitude(rw.getDouble("latitude"));
             entity.setLongitude(rw.getDouble("longitude"));
             entity.setExperties(rw.getString("experties"));
             entity.setHashcode6(rw.getString("hashcode6"));
             entity.setHashcode5(rw.getString("hashcode5"));
             return Optional.of(entity);
         }else{
             return Optional.empty();
         }

    }

}
