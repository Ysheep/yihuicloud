package com.qzdsoft.eshop.domain.advertisement;

import java.util.Date;
import javax.persistence.*;

public class Advertisement {
    /**
     * 广告id
     */
    @Id
    @Column(name = "advert_id")
    private Integer advertId;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "accessory_id")
    private Integer accessoryId;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 获取广告id
     *
     * @return advert_id - 广告id
     */
    public Integer getAdvertId() {
        return advertId;
    }

    /**
     * 设置广告id
     *
     * @param advertId 广告id
     */
    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
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
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}