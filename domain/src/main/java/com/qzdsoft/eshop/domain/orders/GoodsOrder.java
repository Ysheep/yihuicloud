package com.qzdsoft.eshop.domain.orders;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "goods_order")
public class GoodsOrder {
	
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "sku_id")
    private Integer skuId;

    private Integer num;

    private BigDecimal price;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "end_pay_money")
    private BigDecimal endPayMoney;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return total_money
     */
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * @return end_pay_money
     */
    public BigDecimal getEndPayMoney() {
        return endPayMoney;
    }

    /**
     * @param endPayMoney
     */
    public void setEndPayMoney(BigDecimal endPayMoney) {
        this.endPayMoney = endPayMoney;
    }
}