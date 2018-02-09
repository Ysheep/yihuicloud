package com.qzdsoft.eshop.domain.goodsclass;

import javax.persistence.*;

@Table(name = "goods_class")
public class GoodsClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer classId;

    /**
     * 父分类
     */
    private Integer pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 删除状态
     */
    @Column(name = "delete_status")
    private Short deleteStatus;


    /**
     * 关联附件表
     */
    @Column(name = "image_id")
    private Integer imageId;
    
    /**
     * 关联附件表
     */
    @Column(name = "icon_id")
    private Integer iconId;

    /**
     * 如果显示在首页该分类下商品都显示在首页 0 不显示 1显示
     */
    @Column(name = "show_index")
    private Short showIndex;
    
    private Integer seq;

    /**
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取父分类
     *
     * @return pid - 父分类
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父分类
     *
     * @param pid 父分类
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取关联附件表
     *
     * @return image_id - 关联附件表
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * 设置关联附件表
     *
     * @param imageId 关联附件表
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取如果显示在首页该分类下商品都显示在首页 0 不显示 1显示
     *
     * @return show_index - 如果显示在首页该分类下商品都显示在首页 0 不显示 1显示
     */
    public Short getShowIndex() {
        return showIndex;
    }

    /**
     * 设置如果显示在首页该分类下商品都显示在首页 0 不显示 1显示
     *
     * @param showIndex 如果显示在首页该分类下商品都显示在首页 0 不显示 1显示
     */
    public void setShowIndex(Short showIndex) {
        this.showIndex = showIndex;
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}
    
    
}