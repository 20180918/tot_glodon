package com.glodon.seckillcommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * 描述:
 * 分布式锁
 *
 * @author wangpp-b
 * @create 2019-08-13 21:28
 */
public class LockUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockUtil.class);
    static Jedis redis = com.glodon.seckillcommon.utils.RedisUtil.getJedis();
    static Integer expireTime = 50;
    static Long exTime = Long.valueOf(expireTime * 1000);

    public static boolean lock(String lockKey) {
        boolean success = false;
        Long setNxResult = redis.setnx(lockKey, String.valueOf(System.currentTimeMillis()) + exTime);
        if (setNxResult != null && setNxResult.intValue() == 1) {
            success = true;
            LOGGER.info(Thread.currentThread().getName() + "获取锁成功");
            redis.expire(lockKey, expireTime);
        } else {
            String lockValue = redis.get(lockKey);
            if (lockValue != null && System.currentTimeMillis() > Long.parseLong(lockValue)) {
                String newResult = redis.getSet(lockKey, String.valueOf(System.currentTimeMillis()) + exTime);
                if (newResult == null || (newResult != null && newResult.equals(lockValue))) {
                    success = true;
                    LOGGER.info(Thread.currentThread().getName() + "获取锁成功");
                } else {
                    LOGGER.info(Thread.currentThread().getName() + "获取锁失败");
                }
            }
            LOGGER.info(Thread.currentThread().getName() + "获取锁失败");
        }
        return success;
    }

    public static void unLock(String lockKey) {
        LOGGER.info(Thread.currentThread().getName() + "删除锁");
        redis.del(lockKey);
    }

    public static void main(String[] args) {

    }
}