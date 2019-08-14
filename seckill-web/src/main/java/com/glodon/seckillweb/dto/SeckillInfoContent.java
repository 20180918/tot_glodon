package com.glodon.seckillweb.dto;

/**
 * kafka请求信息内容
 *
 * @author wupx
 * @date 2019/08/14
 */
public class SeckillInfoContent {
    /**
     * 商品id
     */
    private Long seckillId;

    private String md5;

    private String userPhone;

    /**
     * 秒杀状态
     */
    private Byte state;

    public SeckillInfoContent() {
    }

    public SeckillInfoContent(Long seckillId, String md5, String userPhone, Byte state) {
        this.seckillId = seckillId;
        this.md5 = md5;
        this.userPhone = userPhone;
        this.state = state;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "SeckillInfoContent{" +
                "seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", state=" + state +
                '}';
    }
}
