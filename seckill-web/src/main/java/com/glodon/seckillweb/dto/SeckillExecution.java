package com.glodon.seckillweb.dto;


/**
 * 描述:
 * 秒杀返回结果
 *
 * @author wangpp-b
 * @create 2019-08-12 16:30
 */
public class SeckillExecution {
    private long seckillId;

    /**
     * 秒杀执行结果的状态
     */
    private int state;

    public SeckillExecution(long seckillId, int state) {
        this.seckillId = seckillId;
        this.state = state;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
