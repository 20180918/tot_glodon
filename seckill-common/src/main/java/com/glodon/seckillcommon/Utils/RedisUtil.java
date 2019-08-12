package com.glodon.seckillcommon.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 描述:
 * redis客户端
 *
 * @author wangpp-b
 * @create 2019-08-12 15:04
 */
public class RedisUtil {

    public static void main(String[] args) {
        RedisUtil.remove("product_detail1000");
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);
    static JedisPool jedisPool = JedisPoolManager.getJedisPoolManager().pool;

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void set(String key, String value) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 设置 byte[]
     *
     * @param key
     * @param value
     */
    public synchronized static void set(byte[] key, byte[] value) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 设置 String 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(String key, String value, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.setex(key, seconds, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set keyex error : " + e);
        }
    }

    /**
     * 设置 byte[] 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(byte[] key, byte[] value, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String get(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 获取byte[]值
     *
     * @param key
     * @return value
     */
    public synchronized static byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        byte[] value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized static void remove(String key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Remove keyex error : " + e);
        }
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized static void remove(byte[] key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Remove keyex error : " + e);
        }
    }

    /**
     * lpush
     *
     * @param key
     * @param key
     */
    public synchronized static void lpush(String key, String... strings) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.lpush(key, strings);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("lpush error : " + e);
        }
    }

    /**
     * lrem
     *
     * @param key
     * @param count
     * @param value
     */
    public synchronized static void lrem(String key, long count, String value) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.lrem(key, count, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("lpush error : " + e);
        }
    }

    /**
     * sadd
     *
     * @param key
     * @param value
     * @param seconds
     */
    public synchronized static void sadd(String key, String value, int seconds) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.sadd(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("sadd error : " + e);
        }
    }

    /**
     * incr
     *
     * @param key
     * @return value
     */
    public synchronized static Long incr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.incr(key);
        jedis.close();
        return value;
    }

    /**
     * decr
     *
     * @param key
     * @return value
     */
    public synchronized static Long decr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.decr(key);
        jedis.close();
        return value;
    }

}
