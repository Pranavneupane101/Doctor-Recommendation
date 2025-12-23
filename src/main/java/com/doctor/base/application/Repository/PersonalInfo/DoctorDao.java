package com.doctor.base.application.Repository.PersonalInfo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.internal.core.type.codec.TimeUuidCodec;
import com.doctor.base.application.Connection.CqlConnection;
import com.doctor.base.core.models.Doctor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class DoctorDao {
    @Inject
    CqlConnection cqlConnection;

    public boolean addDoctor(DoctorEntity doctor) {
        CqlSession session = cqlConnection.getConnection();

        String insertQuery =
                "INSERT INTO doctor (doctor_id, doctor_designation, doctor_name, experties, " +
                        "experience, fee, latitude, longitude, phone_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(doctor.getExperties());
       return session.execute(session.prepare(insertQuery).bind(
               doctor.getDoctorId(),
                doctor.getDoctorDesignation(),
                doctor.getDoctorName(),
                doctor.getExperties(),
                doctor.getExperience(),
                doctor.getFee(),
                doctor.getLatitude(),
                doctor.getLongitude(),
                doctor.getPhone_no()
        )).wasApplied();


    }

    public boolean updateDoctor(DoctorEntity doctor) {
        CqlSession session = cqlConnection.getConnection();
        DoctorEntity foundDoctor =getDoctorById(doctor.getDoctorId());

        if(foundDoctor == null) {
            return false;
        }


        String updateQuery =
                "UPDATE doctor SET " +
                        "doctor_designation = ?, " +
                        "doctor_name = ?, " +
                        "experties = ?, " +
                        "experience = ?, " +
                        "fee = ?, " +
                        "latitude = ?, " +
                        "longitude = ?, " +
                        "phone_no = ? " +
                        "WHERE doctor_id = ?";


        session.execute(session.prepare(updateQuery).bind(
                doctor.getDoctorDesignation(),
                doctor.getDoctorName(),
                doctor.getExperties(),
                doctor.getExperience(),
                doctor.getFee(),
                doctor.getLatitude(),
                doctor.getLongitude(),
                doctor.getPhone_no(),
                 doctor.getDoctorId()
        ));

        return true;
    }


    public DoctorEntity getDoctorById(String doctorId) {
        CqlSession session = cqlConnection.getConnection();
        String query = "SELECT * FROM doctor WHERE doctor_id = ?";

        Row row = session.execute(
                session.prepare(query).bind(doctorId)
        ).one();

        if (row == null) return null;

        DoctorEntity doctor = new DoctorEntity();
        doctor.setDoctorId(row.getString("doctor_id"));
        doctor.setDoctorDesignation(row.getString("doctor_designation"));
        doctor.setDoctorName(row.getString("doctor_name"));
        doctor.setExperties(row.getSet("experties", String.class));
        doctor.setExperience(row.getString("experience"));
        doctor.setFee(row.getString("fee"));
        doctor.setLatitude(row.getDouble("latitude"));
        doctor.setLongitude(row.getDouble("longitude"));
        doctor.setPhone_no(row.getString("phone_no"));

        return doctor;
    }
    public boolean  deleteDoctor(String doctor_id) {
        System.out.println(doctor_id);
        CqlSession session = cqlConnection.getConnection();
        String query = "DELETE FROM doctor WHERE doctor_id = ?  ";
        return session.execute(session.prepare(query).bind(doctor_id)).wasApplied();
    }



}
