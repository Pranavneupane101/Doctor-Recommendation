package com.doctor.base.application.Repository.Availability;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.doctor.base.application.Connection.CqlConnection;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;

import java.util.UUID;

@Singleton
  class AvailabilityDao {
    @Inject
    CqlConnection connection;

    public boolean AddAvailability(AvailabilityEntity availability){
        CqlSession session = connection.getConnection();

        String query= "INSERT INTO doctor_availability " +
                "(experties, doctor_id, date, available_from, available_to, appointments) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return session.execute(session.prepare(query).bind(
                availability.getExperties(),
                UUID.fromString(availability.getDoctorId()),
                availability.getDate(),
                availability.getAvailablefrom(),
                availability.getAvailableTo(),
                availability.getAppointments()
                )).wasApplied();
    }

    public  AvailabilityEntity getAvailability(String doctorId, String experties, LocalDate date){
        CqlSession session = connection.getConnection();
        String query= "SELECT* from doctor_availability where experties = ? and doctor_id = ? and date=? ";
        ResultSet rs=session.execute(session.prepare(query).bind(experties
                , UUID.fromString(doctorId)
                ,date));
        Row rw=rs.one();
        if (rw==null){
            return null;
        }
        AvailabilityEntity availability=new AvailabilityEntity();
        availability.setDoctorId(rw.getString("doctor_id"));
        availability.setExperties(rw.getUuid("experties").toString());
        availability.setDate(rw.getLocalDate("date"));
        return availability;
    }

    public boolean UpdateAvailability(AvailabilityEntity availability){
        CqlSession session = connection.getConnection();
        String query="UPDATE doctor_availability SET " +
                "available_from = ?, " +
                "available_to = ?, " +
                "appointments = ? " +
                "WHERE experties = ? AND doctor_id = ? AND date = ?";

        return session.execute(session.prepare(query).bind(
                availability.getAvailablefrom(),
                availability.getAvailableTo(),
                availability.getAppointments(),
                availability.getDate()
        )).wasApplied();
    }


}
