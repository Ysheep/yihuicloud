package com.qzdsoft.eshop.domain.goods;

import javax.persistence.*;

@Table(name = "comment_img")
public class CommentImg {
    @Id
    @Column(name = "img_id")
    private Integer imgId;

    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "accessory_id")
    private Integer accessoryId;

    /**
     * @return img_id
     */
    public Integer getImgId() {
        return imgId;
    }

    /**
     * @param imgId
     */
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

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
}