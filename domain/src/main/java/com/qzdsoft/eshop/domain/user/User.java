package com.qzdsoft.eshop.domain.user;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "password")
    private String password;
    
    
    @Column(name = "wx_id")
    private String wxId;

    @Column(name = "head_img")
    private String headImg;

    @Column(name = "sex")
    private Integer sex;
    
    @Column(name = "birthday")
    private String birthday;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "true_name")
    private String trueName;

    @Column(name = "reg_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regTime;
    
    private String address;
    
    @Column(name = "referee_id")
    private String refereeId;
    
    private BigDecimal balance;
    
    @Column(name = "freeze_money")
    private BigDecimal freezeMoney;
    
    private Integer level;

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
     * @return wx_id
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * @param wxId
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    /**
     * @return head_img
     */
    public String getHeadImg() {
        return headImg;
    }
    
    /**
     * @param headImg
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
     * @return reg_time
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
    
    
}