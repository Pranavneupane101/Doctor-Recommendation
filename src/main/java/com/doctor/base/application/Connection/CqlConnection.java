package com.doctor.base.application.Connection;

import com.datastax.oss.driver.api.core.CqlSession;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CqlConnection {
    @Inject
    private final CqlSession session;

    public CqlConnection(CqlSession session) {
        this.session = CqlSession.builder()
                .withKeyspace("doctor_db")
                .build();
    }


    public CqlSession getConnection(){

        return session;
    }
}
