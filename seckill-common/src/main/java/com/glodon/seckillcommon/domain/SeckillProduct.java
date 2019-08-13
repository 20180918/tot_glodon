package com.glodon.seckillcommon.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * seckill_product
 * @author 
 */
public class SeckillProduct implements Serializable {
    /**
     * 商品库存ID
     */
    private String seckillId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 剩余库存数量
     */
    private Integer number;

    /**
     * 秒杀开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品编码
     */
    private String productCode;

    private BigDecimal productPrice;

    private BigDecimal seckillPrice;

    private String productDescription;

    private String productImg;

    /**
     * -1:未上架，0：已下架，1：已上架
     */
    private Integer productState;

    private Integer productStocks;

    private static final long serialVersionUID = 1L;

    public String getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(String seckillId) {
        this.seckillId = seckillId;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Integer getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(Integer productStocks) {
        this.productStocks = productStocks;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SeckillProduct other = (SeckillProduct) that;
        return (this.getSeckillId() == null ? other.getSeckillId() == null : this.getSeckillId().equals(other.getSeckillId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getSeckillPrice() == null ? other.getSeckillPrice() == null : this.getSeckillPrice().equals(other.getSeckillPrice()))
            && (this.getProductDescription() == null ? other.getProductDescription() == null : this.getProductDescription().equals(other.getProductDescription()))
            && (this.getProductImg() == null ? other.getProductImg() == null : this.getProductImg().equals(other.getProductImg()))
            && (this.getProductState() == null ? other.getProductState() == null : this.getProductState().equals(other.getProductState()))
            && (this.getProductStocks() == null ? other.getProductStocks() == null : this.getProductStocks().equals(other.getProductStocks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSeckillId() == null) ? 0 : getSeckillId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getSeckillPrice() == null) ? 0 : getSeckillPrice().hashCode());
        result = prime * result + ((getProductDescription() == null) ? 0 : getProductDescription().hashCode());
        result = prime * result + ((getProductImg() == null) ? 0 : getProductImg().hashCode());
        result = prime * result + ((getProductState() == null) ? 0 : getProductState().hashCode());
        result = prime * result + ((getProductStocks() == null) ? 0 : getProductStocks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seckillId=").append(seckillId);
        sb.append(", name=").append(name);
        sb.append(", number=").append(number);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", productCode=").append(productCode);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", seckillPrice=").append(seckillPrice);
        sb.append(", productDescription=").append(productDescription);
        sb.append(", productImg=").append(productImg);
        sb.append(", productState=").append(productState);
        sb.append(", productStocks=").append(productStocks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}