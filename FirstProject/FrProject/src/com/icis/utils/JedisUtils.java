package com.icis.utils;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/22 13:57
 */
public class JedisUtils {
    private static JedisPoolConfig  poolConfig;
    private static Properties properties= new Properties();
    private static JedisPool jedisPool;


    static{

        try {
            InputStream resourceAsStream = JedisPoolConfig.class.getResourceAsStream("/jedis.properties");
            properties= new Properties();
            properties.load(resourceAsStream);
            poolConfig=new JedisPoolConfig();
            //设置参数
            poolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
            poolConfig.setMaxWaitMillis(Long.parseLong(properties.getProperty("MaxWaitlMillis")));
            poolConfig.setMinIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            jedisPool = new JedisPool(poolConfig,"localhost");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
    @Test
    public void testGetJedis(){
        Jedis jedis = getJedis();
        jedis.close();
    }
}
