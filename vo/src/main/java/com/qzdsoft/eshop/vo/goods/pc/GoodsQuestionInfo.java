package com.qzdsoft.eshop.vo.goods.pc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsQuestionInfo {

	private Integer questionId;
	
	private String userName;
	
	private String content;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private String ctime;
	
	private List<GoodsAnswerInfo> answer;
	
	private Integer count;
	
	
	public Integer getCount() {
		return count;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public List<GoodsAnswerInfo> getAnswer() {
		return answer;
	}

	public void setAnswer(List<GoodsAnswerInfo> answer) {
		this.count = answer.size();
		this.answer = answer;
	}
	
	
}
