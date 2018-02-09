package com.qzdsoft.eshop.vo.advertisement;

public class AdvertisementInfo {
	
	 /**
     * 广告id
     */
    private Integer advertId;

    private Integer goodsId;
    
    private String goodsName;

    private Integer accessoryId;
    
    private String url;
    /**
     * 创建时间
     */
    private String ctime;
    
	public Integer getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Integer advertId) {
		this.advertId = advertId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
    
    
}
