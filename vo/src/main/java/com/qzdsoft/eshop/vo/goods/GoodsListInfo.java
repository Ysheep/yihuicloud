/**
 * 
 */
package com.qzdsoft.eshop.vo.goods;

import java.math.BigDecimal;

/**
 * 前台商品分类页面商品信息
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public class GoodsListInfo
{

    private String name;//商品名称
    private String image;//商品图片
    private Integer id;//商品id
    private Integer skuId;
    private BigDecimal price;//商品单价
    private Integer purchaseNum;//购买人数
    private Integer classType;
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return the image
     */
    public String getImage()
    {
        return image;
    }
    /**
     * @param image the image to set
     */
    public void setImage(String image)
    {
        this.image = image;
    }
    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    /**
     * @return the price
     */
    public BigDecimal getPrice()
    {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    /**
     * @return the purchaseNum
     */
    public Integer getPurchaseNum()
    {
        return purchaseNum;
    }
    /**
     * @param purchaseNum the purchaseNum to set
     */
    public void setPurchaseNum(Integer purchaseNum)
    {
        this.purchaseNum = purchaseNum;
    }
	public Integer getClassType() {
		return classType;
	}
	public void setClassType(Integer classType) {
		this.classType = classType;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
}
