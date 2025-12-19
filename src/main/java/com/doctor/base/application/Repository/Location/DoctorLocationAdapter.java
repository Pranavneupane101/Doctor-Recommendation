package com.doctor.base.application.Repository.Location;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
  class DoctorLocationAdapter implements LocationRepositoryPort{
    @Inject
    DoctorLocationDao doctorLocationDao;
    @Override
    public boolean AddLocation(DoctorLocation location) {
        return doctorLocationDao.AddDoctorLocation(DoctorLocationMapper.toEntity(location));
    }

    @Override
    public boolean UpdateLocation(DoctorLocation location) {
        return doctorLocationDao.UpdateDoctorLocation(DoctorLocationMapper.toEntity(location));
    }

    @Override
    public boolean DeleteLocation() {

        return true;
    }

    @Override
    public ResultSet getDoctorNarrowLocations(String experties, String geoHash5, String geoHash6) {
        return doctorLocationDao.getDoctorLocationListNarrow(experties,geoHash5,geoHash6);
    }

    @Override
    public ResultSet getDoctorBroadLocations(String experties, String geoHash5) {
       return doctorLocationDao.getDoctorLocationListBroad(experties,geoHash5);
    }
}
