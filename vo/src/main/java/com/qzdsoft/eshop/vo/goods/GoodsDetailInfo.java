/**
 * 
 */
package com.qzdsoft.eshop.vo.goods;

import java.math.BigDecimal;
import java.util.List;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public class GoodsDetailInfo
{
    private Integer id;
    private Integer skuId;
    private List<String> images;
    private String name;
    private String descript;//产品描述
    private String decode; //产品编码
    private BigDecimal price;
    private Integer purchaseNum;
    private String videoUrl;//视频文件
    private String detail;
    private List<GoodsPropertiesInfo> props;
    /**
     * @return the images
     */
    public List<String> getImages()
    {
        return images;
    }
    /**
     * @param images the images to set
     */
    public void setImages(List<String> images)
    {
        this.images = images;
    }
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
    
    public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getDecode() {
		return decode;
	}
	public void setDecode(String decode) {
		this.decode = decode;
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
    /**
     * @return the props
     */
    public List<GoodsPropertiesInfo> getProps()
    {
        return props;
    }
    /**
     * @param props the props to set
     */
    public void setProps(List<GoodsPropertiesInfo> props)
    {
        this.props = props;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
    
}
