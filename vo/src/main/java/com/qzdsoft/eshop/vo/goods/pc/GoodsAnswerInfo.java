package com.qzdsoft.eshop.vo.goods.pc;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsAnswerInfo {
	
	private String userName;
	
	private String content;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private String ctime;

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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
}
