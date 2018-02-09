package com.qzdsoft.eshop.vo.goods.pc;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 商品评论
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月28日
 * @see
 * @since 1.0
 */
public class GoodsCommentInfo {
	
	private String commentId;
	private String userName;
	private String content;
	private List<String> imageUrl;
	private String specIds;//商品属性
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private String ctime;
	
	
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return userName.substring(0, 1)+"***"+userName.substring(userName.length()-1, userName.length());
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getSpecIds() {
		return specIds;
	}
	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	
	
}
