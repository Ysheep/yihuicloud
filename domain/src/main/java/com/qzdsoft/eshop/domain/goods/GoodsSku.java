package com.qzdsoft.eshop.domain.goods;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "goods_sku")
public class GoodsSku {
    /**
     * sku id
     */
    @Id
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 图片id
     */
    @Column(name = "accessory_id")
    private Integer accessoryId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 名称
     */
    private String name;

    /**
     * 属性值id列表用逗号分隔
     */
    @Column(name = "spec_ids")
    private String specIds;

    @Column(name = "delete_status")
    private Integer deleteStatus;
    /**
     * 类型 : 0 非默认 1 默认
     */
    private Integer type;
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
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取图片id
     *
     * @return accessory_id - 图片id
     */
    public Integer getAccessoryId() {
        return accessoryId;
    }

    /**
     * 设置图片id
     *
     * @param accessoryId 图片id
     */
    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性值id列表用逗号分隔
     *
     * @return spec_ids - 属性值id列表用逗号分隔
     */
    public String getSpecIds() {
        return specIds;
    }

    /**
     * 设置属性值id列表用逗号分隔
     *
     * @param specIds 属性值id列表用逗号分隔
     */
    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    
}