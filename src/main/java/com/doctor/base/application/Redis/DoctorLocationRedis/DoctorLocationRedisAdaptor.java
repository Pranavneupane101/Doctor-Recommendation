package com.doctor.base.application.Redis.DoctorLocationRedis;

import com.doctor.base.application.Connection.JedisConnectoin;
import com.doctor.base.core.models.DoctorLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import redis.clients.jedis.Jedis;


public class DoctorLocationRedisAdaptor implements DoctorLocationRedisPort {

    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    private final String PREFIX="DoctorLocation:";
    @Inject
    public DoctorLocationRedisAdaptor(JedisConnectoin jedisConnectoin, ObjectMapper objectMapper) {
        this.jedis = jedisConnectoin.getJedis();
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean AddLocation(DoctorLocation location) {
        String key=PREFIX+":"+location.getExperties()+":"+location.getHashcode6()+":"+location.getHashcode5()+":"+location.getDoctorId();

        try{
        jedis.set(key,objectMapper.writeValueAsString(location));
        return true;
            }catch(Exception e){
        e.printStackTrace();
        return false;}
    }

    @Override
    public boolean UpdateLocation(DoctorLocation location) {
        String key=PREFIX+":"+location.getExperties()+":"+location.getHashcode6()+":"+location.getHashcode5()+":"+location.getDoctorId();
        if (jedis.exists(key)){
            jedis.del(key);}
            return AddLocation(location);
        }


}




