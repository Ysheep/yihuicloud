package com.qzdsoft.eshop.domain.goods;

import javax.persistence.*;

@Table(name = "goods_img")
public class GoodsImg {
    @Column(name = "accessory_id")
    private Integer accessoryId;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "is_master_img")
    private Short isMasterImg;

    /**
     * @return accessory_id
     */
    public Integer getAccessoryId() {
        return accessoryId;
    }

    /**
     * @param accessoryId
     */
    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    /**
     * @return goods_id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * @return is_master_img
     */
    public Short getIsMasterImg() {
        return isMasterImg;
    }

    /**
     * @param isMasterImg
     */
    public void setIsMasterImg(Short isMasterImg) {
        this.isMasterImg = isMasterImg;
    }
}