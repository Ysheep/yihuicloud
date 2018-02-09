package com.qzdsoft.eshop.domain.goods;

import javax.persistence.*;

@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "user_id")
    private Integer userId;

    private Integer num;

    /**
     * sku id
     */
    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "delete_status")
    private Short deleteStatus;

    /**
     * @return cart_id
     */
    public Integer getCartId() {
        return cartId;
    }

    /**
     * @param cartId
     */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取sku id
     *
     * @return sku_id - sku id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置sku id
     *
     * @param skuId sku id
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return delete_status
     */
    public Short getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * @param deleteStatus
     */
    public void setDeleteStatus(Short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}