package com.doctor.base.application.Redis.DoctorRedis;

import com.doctor.base.application.Connection.JedisConnectoin;
import com.doctor.base.core.models.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;


@Singleton
public class DoctorRedisAdaptor implements DoctorRedisPort{


    private final Jedis jedis;
    private final ObjectMapper objectMapper;
    private final String DOCTOR_PREFIX="Doctor:";
    @Inject
    public  DoctorRedisAdaptor(JedisConnectoin jedisConnectoin,ObjectMapper objectMapper){
        this.jedis=jedisConnectoin.getJedis();
        this.objectMapper=objectMapper;
    }


    @Override
    public boolean AddDoctor(Doctor doctor) {
        String key=DOCTOR_PREFIX+doctor.getDoctorId();


        try {
            jedis.set(key, objectMapper.writeValueAsString(doctor));
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean UpdateDoctor(Doctor doctor) {
        String key =DOCTOR_PREFIX+":"+doctor.getDoctorId();
        if (jedis.exists(key)){
            jedis.del(key);}
        return AddDoctor(doctor);
    }


    @Override
    public  Doctor findDoctor(String doctor_id ) {
        String json =jedis.get(DOCTOR_PREFIX+":"+doctor_id);
        if (json!=null){
            try {

                return objectMapper.readValue(json, Doctor.class);
            }catch(Exception e){

                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;

    }

    @Override
    public boolean deleteDoctor(String doctor_id) {
        String key =DOCTOR_PREFIX+":"+doctor_id;
          jedis.del(key);
          return true;

    }
}
