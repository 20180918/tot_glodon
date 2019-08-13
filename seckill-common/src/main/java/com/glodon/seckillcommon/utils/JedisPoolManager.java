package com.glodon.seckillcommon.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 描述:
 * 双检锁实现单例模式Jedis连接池
 *
 * @author wangpp-b
 * @create 2019-08-12 13:27
 */
public class JedisPoolManager {
    private volatile static JedisPoolManager jedisPoolManager;

    public final JedisPool pool;

    public static JedisPoolManager getJedisPoolManager() {
        if (jedisPoolManager == null) {
            synchronized (JedisPoolManager.class) {
                if (jedisPoolManager == null) {
                    jedisPoolManager = new JedisPoolManager();
                }
            }
        }
        return jedisPoolManager;
    }

    private JedisPoolManager() {
        try {
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置池配置项值
            config.setMaxTotal(20);
            config.setMaxIdle(10);
            config.setMinIdle(1);
            config.setMaxWaitMillis(60000);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            // 根据配置实例化jedis池
            System.out.println("***********init JedisPool***********");
            pool = new JedisPool(config, "106.14.13.61", 6379);
        } catch (Exception e) {
            throw new IllegalArgumentException("init JedisPool error", e);
        }
    }

    public static void main(String[] args) {


    }


}
