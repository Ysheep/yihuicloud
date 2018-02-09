package com.qzdsoft.eshop.domain.goods;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * sku id
     */
    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "goods_id")
    private Integer goodsId;

    private Date ctime;

    private String content;

    /**
     * @return comment_id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}