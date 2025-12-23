package com.doctor.base.application.Connection;

import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Singleton
public class JedisConnectoin {

    private final  JedisPool jedisPool;

    public JedisConnectoin  (){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(500);
        jedisPoolConfig.setMaxIdle(500);
        jedisPoolConfig.setMinIdle(500);

        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        this.jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);
    }

    public Jedis getJedis(){

        return this.jedisPool.getResource();

    }
}
