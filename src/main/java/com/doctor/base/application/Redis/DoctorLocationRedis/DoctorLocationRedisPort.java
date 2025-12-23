package com.doctor.base.application.Redis.DoctorLocationRedis;


import com.doctor.base.core.models.DoctorLocation;


import java.util.Set;

public interface DoctorLocationRedisPort {
    boolean AddLocation(DoctorLocation location);
    boolean UpdateLocation(DoctorLocation location);

}
