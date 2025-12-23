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
                        "geohash5, experties, geohash6, doctorid, latitude, longitude" +
                        ") VALUES (?, ?, ?, ?, ?, ?)";

        return session.execute(session.prepare(insertQuery).bind(
                doctor.getHashcode5(),
                doctor.getExperties(),
                doctor.getHashcode6(),
                doctor.getDoctorId(),
                doctor.getLatitude(),
                doctor.getLongitude()
        )).wasApplied();
    }

    public boolean UpdateDoctorLocation(LocationEntity doctor){

        CqlSession session = connection.getConnection();

        String updateQuery =
                "UPDATE doctor_location SET " +
                        "latitude = ?, longitude = ? " +
                        "WHERE geohash5 = ? AND experties = ? AND geohash6 = ? AND doctorid = ?";

        return session.execute(session.prepare(updateQuery).bind(
                doctor.getLatitude(),
                doctor.getLongitude(),
                doctor.getHashcode5(),
                doctor.getExperties(),
                doctor.getHashcode6(),
                 doctor.getDoctorId()
        )).wasApplied();

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

}
