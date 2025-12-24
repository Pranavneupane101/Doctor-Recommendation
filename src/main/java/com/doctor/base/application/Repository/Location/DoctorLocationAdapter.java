package com.doctor.base.application.Repository.Location;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.doctor.base.core.models.DoctorLocation;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.xml.stream.Location;
import java.util.Optional;

@Singleton
  class DoctorLocationAdapter implements LocationRepositoryPort{
    @Inject
    DoctorLocationDao doctorLocationDao;
    @Override
    public void AddLocation(DoctorLocation location) {
          doctorLocationDao.AddDoctorLocation(DoctorLocationMapper.toEntity(location));
    }

    @Override
    public Optional<DoctorLocation> UpdateLocation(DoctorLocation location) {
         Optional<LocationEntity> entity= doctorLocationDao.UpdateDoctorLocation(DoctorLocationMapper.toEntity(location));
          return entity.map(DoctorLocationMapper::fromEntity);
    }



    @Override
    public ResultSet getDoctorNarrowLocations(String experties, String geoHash5, String geoHash6) {
        return doctorLocationDao.getDoctorLocationListNarrow(experties,geoHash5,geoHash6);
    }

    @Override
    public ResultSet getDoctorBroadLocations(String experties, String geoHash5) {
       return doctorLocationDao.getDoctorLocationListBroad(experties,geoHash5);
    }

    @Override
    public boolean DeleteLocation(String geohash5, String experties, String geohash6, String doctorId) {
        return doctorLocationDao.deleteDoctorLocation(geohash5,experties,geohash6,doctorId);
    }


}
