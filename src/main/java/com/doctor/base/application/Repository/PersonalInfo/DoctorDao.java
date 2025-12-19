package com.doctor.base.application.Repository.PersonalInfo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.doctor.base.application.Connection.CqlConnection;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class DoctorDao {
    @Inject
    CqlConnection cqlConnection;

    public boolean addDoctor(DoctorEntity doctor){
        CqlSession session=cqlConnection.getConnection();

        String insertQuery =
                "INSERT INTO doctor (" +
                        "doctor_id, doctor_designation, doctor_name, experties, experience, fee, location" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        return session.execute(session.prepare(insertQuery).bind(
                UUID.randomUUID(),
              doctor.getDoctorDesignation(),
              doctor.getDoctorName(),
              doctor.getExperties(),
              doctor.getExperience(),
                doctor.getFee(),
                doctor.getLocation()
        )).wasApplied();

    }

    public boolean updateDoctor(DoctorEntity doctor){
        CqlSession session=cqlConnection.getConnection();
        String updateQuery =
                "UPDATE doctor SET " +
                        "doctor_designation = ?, " +
                        "doctor_name = ?, " +
                        "experience = ?, " +
                        "fee = ?, " +
                        "location = ? " +
                        "WHERE experties=? AND doctor_id = ?";
       return session.execute(session.prepare(updateQuery).bind(

                doctor.getDoctorDesignation(),
                doctor.getDoctorName(),
                doctor.getExperience(),
                doctor.getFee(),
                doctor.getLocation(),
               doctor.getExperties(),
               UUID.fromString(doctor.getDoctorId())

        )).wasApplied();
    }


    public DoctorEntity getDoctorById(String doctorId,String experties){
        CqlSession session=cqlConnection.getConnection();
        String query="SELECT * FROM doctor WHERE experties=? AND doctor_id = ?";
         ResultSet rs  =session.execute(session.prepare(query)
                .bind(experties,
                        UUID.fromString(doctorId)));
        Row row = rs.one();
        if(row==null){
            return null;
        }
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setDoctorId(row.getUuid("doctor_id").toString());
        doctorEntity.setDoctorDesignation(row.getString("doctor_designation"));
        doctorEntity.setExperience(row.getString("experience"));
        doctorEntity.setDoctorName(row.getString("doctor_name"));
        doctorEntity.setExperties(row.getString("experties"));
        doctorEntity.setFee(row.getString("fee"));
        doctorEntity.setLocation(row.getString("location"));
        return doctorEntity;



    }



}
