package com.glodon.seckilladmin.vo;

import java.math.BigDecimal;

public class SecKillProductVo {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 剩余库存数量
     */
    private Integer number;
    /**
     * 商品编码
     */
    private String productCode;
    private BigDecimal seckillPrice;
    /**
     * -1:未上架，0：已下架，1：已上架
     */
    private Integer productState;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    @Override
    public String toString() {
        return "SecKillProductVo{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", productCode='" + productCode + '\'' +
                ", seckillPrice=" + seckillPrice +
                ", productState=" + productState +
                '}';
    }
}
