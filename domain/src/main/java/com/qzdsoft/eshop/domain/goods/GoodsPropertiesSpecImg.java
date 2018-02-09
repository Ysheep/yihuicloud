package com.qzdsoft.eshop.domain.goods;

import javax.persistence.*;

@Table(name = "goods_properties_spec_img")
public class GoodsPropertiesSpecImg {
    /**
     * 商品属性值图片id
     */
    @Id
    @Column(name = "spec_img_id")
    private Integer specImgId;

    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 属性值id
     */
    @Column(name = "spec_id")
    private Integer specId;

    /**
     * 附件id
     */
    @Column(name = "accessory_id")
    private Integer accessoryId;

    /**
     * 获取商品属性值图片id
     *
     * @return spec_img_id - 商品属性值图片id
     */
    public Integer getSpecImgId() {
        return specImgId;
    }

    /**
     * 设置商品属性值图片id
     *
     * @param specImgId 商品属性值图片id
     */
    public void setSpecImgId(Integer specImgId) {
        this.specImgId = specImgId;
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
     * 获取属性值id
     *
     * @return spec_id - 属性值id
     */
    public Integer getSpecId() {
        return specId;
    }

    /**
     * 设置属性值id
     *
     * @param specId 属性值id
     */
    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    /**
     * 获取附件id
     *
     * @return accessory_id - 附件id
     */
    public Integer getAccessoryId() {
        return accessoryId;
    }

    /**
     * 设置附件id
     *
     * @param accessoryId 附件id
     */
    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }
}