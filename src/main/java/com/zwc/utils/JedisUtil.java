package com.zwc.utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-09 11:15
 * @Description: //TODO jedis工具包
 **/
public class JedisUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String APPLICATION_YML = "/application.yml";

    /* 定义一个全局的jedis实例*/
    private static Jedis jedis = null;

    /* 定义jedis连接池*/
    private static JedisPool jedisPool;

    /* 定义Redis服务器地址*/
    private static String redisHost;

    /* 定义Redis服务器连接端口*/
    private static int redisPort;

    /* 定义Redis数据库索引（默认为0）*/
    private static int redisDataBase;

    /* 定义连接超时时间（毫秒）*/
    private static int redisTimeOut;

    /* 定义Redis服务器连接密码（默认为空）*/
    private static String redisPassword;

    /* 定义连接池最大连接数（使用负值表示没有限制）*/
    private static int maxActive;

    /* 定义连接池最大阻塞等待时间（使用负值表示没有限制）*/
    private static int maxWait;

    /* 定义连接池中的最大空闲连接*/
    private static int maxIdle;

    /* 定义连接池中的最小空闲连接*/
    private static int minIdle;

    /* 加载各个属性*/
    static {
        redisHost = ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "host");
        redisPassword = ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "password");
        redisPort = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "port"));
        redisDataBase = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "database"));
        redisTimeOut = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "timeout"));
        maxActive = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "max-active"));
        maxWait = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "max-wait"));
        maxIdle = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "max-idle"));
        minIdle = Integer.valueOf(ConfigPropertyUtil.getValueByKey(APPLICATION_YML, "min-idle"));
    }

    /* 创建连接池*/
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, redisTimeOut, StringUtils.isBlank(redisPassword) ? null : redisPassword, redisDataBase);
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 13:38 2019/8/9
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 获取jedis实例，如果实例为空，则获取，不为空则直接拿到实例。
     **/
    public static Jedis getJedis() {
        return (jedis == null) ? jedis = jedisPool.getResource() : jedis;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 14:20 2019/8/9
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 释放jedis连接到连接池中
     **/
    public static void closeJedis() {
        if (jedis != null) jedis.close();
    }

}
