package com.qzdsoft.eshop.domain.goods;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 分类id
     */
    @Column(name = "class_id")
    private Integer classId;
    /**
     * 运费模板id
     */
    @Column(name = "template_id")
    private Integer templateId;

    /**
     * 商品名称
     */
    private String name;
    
    private String descript;
    
    private String decode;

    /**
     * 购买数量
     */
    @Column(name = "purchase_num")
    private Integer purchaseNum;

    
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 上架时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 下架时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 删除状态
     */
    @Column(name = "delete_status")
    private Short deleteStatus;

    /**
     * 详情
     */
    private String detail;
    
    /**
     * 是否推荐  1 推荐  0 不推荐
     */
    private Integer isRecommend;
    
    @Column(name = "vedio_id")
    private Integer vedio;
    
    private Integer isPackage;
    
    private Integer packageIndexShow;
    
    @Column(name = "system_id")
    private Integer systemId;

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
     * 获取分类id
     *
     * @return class_id - 分类id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置分类id
     *
     * @param classId 分类id
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
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
     * 获取购买数量
     *
     * @return purchase_num - 购买数量
     */
    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    /**
     * 设置购买数量
     *
     * @param purchaseNum 购买数量
     */
    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
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

    /**
     * 获取上架时间
     *
     * @return start_time - 上架时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置上架时间
     *
     * @param startTime 上架时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取下架时间
     *
     * @return end_time - 下架时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置下架时间
     *
     * @param endTime 下架时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取删除状态
     *
     * @return delete_status - 删除状态
     */
    public Short getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置删除状态
     *
     * @param deleteStatus 删除状态
     */
    public void setDeleteStatus(Short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 获取详情
     *
     * @return detail - 详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详情
     *
     * @param detail 详情
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	public Integer getVedio() {
		return vedio;
	}

	public void setVedio(Integer vedio) {
		this.vedio = vedio;
	}

	public Integer getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}

	public Integer getPackageIndexShow() {
		return packageIndexShow;
	}

	public void setPackageIndexShow(Integer packageIndexShow) {
		this.packageIndexShow = packageIndexShow;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	
	
}