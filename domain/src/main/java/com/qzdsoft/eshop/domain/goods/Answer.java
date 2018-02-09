package com.qzdsoft.eshop.domain.goods;

import java.util.Date;
import javax.persistence.*;

public class Answer {
    /**
     * 回答id
     */
    @Id
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question_id")
    private Integer questionId;

    private Date ctime;

    private String content;

    /**
     * 获取回答id
     *
     * @return answer_id - 回答id
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * 设置回答id
     *
     * @param answerId 回答id
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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
     * @return question_id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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