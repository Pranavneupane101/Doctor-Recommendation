package com.doctor.base.application.Repository.Availability;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.doctor.base.application.Connection.CqlConnection;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Singleton
  class AvailabilityDao {
    @Inject
    CqlConnection connection;

    public boolean AddAvailability(AvailabilityEntity availability){
        CqlSession session = connection.getConnection();

        String query= "INSERT INTO doctor_availability " +
                "(doctor_id, date, available_from, available_to, appointments) " +
                "VALUES (?, ?, ?, ?, ?)";

        return session.execute(session.prepare(query).bind(
                availability.getDoctorId(),
                availability.getDate(),
                availability.getAvailablefrom(),
                availability.getAvailableTo(),
                availability.getAppointments()
                )).wasApplied();
    }

    public Optional<AvailabilityEntity> getAvailability(String doctorId, LocalDate date){
        CqlSession session = connection.getConnection();
        String query= "SELECT* from doctor_availability where doctor_id = ? AND date=? ";
        ResultSet rs=session.execute(session.prepare(query).bind(
                 doctorId,date
                 ));
        Row rw=rs.one();
        if (rw==null){
            return Optional.empty();
        }
        AvailabilityEntity availability=new AvailabilityEntity();
        availability.setDoctorId(rw.getString("doctor_id"));

        availability.setDate(rw.getLocalDate("date"));
        availability.setAvailablefrom(rw.getLocalTime("available_from"));
        availability.setAvailableTo(rw.getLocalTime("available_to"));
        availability.setAppointments(rw.getList("appointments", LocalTime.class));
        return Optional.of(availability);
    }

    public Optional<AvailabilityEntity> UpdateAvailability(AvailabilityEntity availability){
        CqlSession session = connection.getConnection();
        String query="UPDATE doctor_availability SET " +
                "available_from = ?, " +
                "available_to = ?, " +
                "appointments = ? " +
                "WHERE doctor_id = ? AND date = ?";

        boolean status= session.execute(session.prepare(query).bind(
                availability.getAvailablefrom(),
                availability.getAvailableTo(),
                availability.getAppointments(),
                availability.getDoctorId(),
                availability.getDate()
        )).wasApplied();
        if(status){
            return getAvailability(availability.doctorId,availability.date);
        }else{
            return Optional.empty();
        }
    }
    public boolean DeleteAvailability(String doctor_Id, LocalDate availability_date){
        CqlSession session = connection.getConnection();
        String query="DELETE from doctor_availability where doctor_id = ? AND date =?";
        return session.execute(session.prepare(query).bind(doctor_Id,availability_date)).wasApplied();
    }


}
