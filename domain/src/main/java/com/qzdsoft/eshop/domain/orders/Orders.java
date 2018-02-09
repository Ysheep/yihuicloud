package com.qzdsoft.eshop.domain.orders;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

public class Orders {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 收货地址
     */
    @Column(name = "address_info")
    private String addressInfo;

    /**
     * 收货人
     */
    @Column(name = "true_name")
    private String trueName;

    /**
     * 联系手机
     */
    private String phone;

    /**
     * 邮编
     */
    private String zip;

    /**
     * 0微信 1支付宝 2微信+余额 3支付宝+余额
     */
    @Column(name = "pay_type")
    private Short payType;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;
    
    @Column(name="reminding_deliver")
    private Short remindingDeliver;

    /**
     * 支付价格
     */
    @Column(name = "pay_price")
    private BigDecimal payPrice;

    private BigDecimal carrige;

    @Column(name = "end_carrige")
    private BigDecimal endCarrige;

    /**
     * 0待付款 1付款成功  2退款中 3退款成功 4已发货 5已签收 6拒绝退款 7交易关闭
     */
    private Short status;


    /**
     * 分润状态0未分润,1已分润
     */
    @Column(name = "split_status")
    private Short splitStatus;

    /**
     * 0未删除 1已删除
     */
    @Column(name = "delete_status")
    private Short deleteStatus;

    @Column(name = "online_pay_money")
    private BigDecimal onlinePayMoney;

    /**
     * 备注
     */
    @Column(name = "balance_pay_money")
    private BigDecimal balancePayMoney;

    @Column(name = "user_remark")
    private String userRemark;

    /**
     * 商家备注
     */
    @Column(name = "admin_remark")
    private String adminRemark;
    
    private Date ctime;
    
    @Column(name = "end_time")
    private Date endTime;
    
    //发票  0 不开发票 1 个人发票 2 单位发票
    private short invoice;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取收货地址
     *
     * @return address_info - 收货地址
     */
    public String getAddressInfo() {
        return addressInfo;
    }

    /**
     * 设置收货地址
     *
     * @param addressInfo 收货地址
     */
    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * 获取收货人
     *
     * @return true_name - 收货人
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 设置收货人
     *
     * @param trueName 收货人
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * 获取联系手机
     *
     * @return phone - 联系手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系手机
     *
     * @param phone 联系手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮编
     *
     * @return zip - 邮编
     */
    public String getZip() {
        return zip;
    }

    /**
     * 设置邮编
     *
     * @param zip 邮编
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * 获取0微信 1支付宝 2微信+余额 3支付宝+余额
     *
     * @return pay_type - 0微信 1支付宝 2微信+余额 3支付宝+余额
     */
    public Short getPayType() {
        return payType;
    }

    /**
     * 设置0微信 1支付宝 2微信+余额 3支付宝+余额
     *
     * @param payType 0微信 1支付宝 2微信+余额 3支付宝+余额
     */
    public void setPayType(Short payType) {
        this.payType = payType;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取支付价格
     *
     * @return pay_price - 支付价格
     */
    public BigDecimal getPayPrice() {
        return payPrice;
    }

    /**
     * 设置支付价格
     *
     * @param payPrice 支付价格
     */
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    /**
     * @return carrige
     */
    public BigDecimal getCarrige() {
        return carrige;
    }

    /**
     * @param carrige
     */
    public void setCarrige(BigDecimal carrige) {
        this.carrige = carrige;
    }

    /**
     * @return end_carrige
     */
    public BigDecimal getEndCarrige() {
        return endCarrige;
    }

    /**
     * @param endCarrige
     */
    public void setEndCarrige(BigDecimal endCarrige) {
        this.endCarrige = endCarrige;
    }

    /**
     * 获取0待付款 1付款成功  2退款中 3退款成功 4已发货 5已签收 6拒绝退款 7交易关闭
     *
     * @return status - 0待付款 1付款成功  2退款中 3退款成功 4已发货 5已签收 6拒绝退款 7交易关闭
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置0待付款 1付款成功  2退款中 3退款成功 4已发货 5已签收 6拒绝退款 7交易关闭
     *
     * @param status 0待付款 1付款成功  2退款中 3退款成功 4已发货 5已签收 6拒绝退款 7交易关闭
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取分润状态0未分润,1已分润
     *
     * @return split_status - 分润状态0未分润,1已分润
     */
    public Short getSplitStatus() {
        return splitStatus;
    }

    /**
     * 设置分润状态0未分润,1已分润
     *
     * @param splitStatus 分润状态0未分润,1已分润
     */
    public void setSplitStatus(Short splitStatus) {
        this.splitStatus = splitStatus;
    }

    /**
     * 获取0未删除 1已删除
     *
     * @return delete_status - 0未删除 1已删除
     */
    public Short getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置0未删除 1已删除
     *
     * @param deleteStatus 0未删除 1已删除
     */
    public void setDeleteStatus(Short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * @return online_pay_money
     */
    public BigDecimal getOnlinePayMoney() {
        return onlinePayMoney;
    }

    /**
     * @param onlinePayMoney
     */
    public void setOnlinePayMoney(BigDecimal onlinePayMoney) {
        this.onlinePayMoney = onlinePayMoney;
    }

    /**
     * 获取备注
     *
     * @return balance_pay_money - 备注
     */
    public BigDecimal getBalancePayMoney() {
        return balancePayMoney;
    }

    /**
     * 设置备注
     *
     * @param balancePayMoney 备注
     */
    public void setBalancePayMoney(BigDecimal balancePayMoney) {
        this.balancePayMoney = balancePayMoney;
    }

    /**
     * @return user_remark
     */
    public String getUserRemark() {
        return userRemark;
    }

    /**
     * @param userRemark
     */
    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    /**
     * 获取支付时间
     *
     * @return admin_remark - 支付时间
     */
    public String getAdminRemark() {
        return adminRemark;
    }

    /**
     * 设置支付时间
     *
     * @param adminRemark 支付时间
     */
    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

	public Short getRemindingDeliver() {
		return remindingDeliver;
	}

	public void setRemindingDeliver(Short remindingDeliver) {
		this.remindingDeliver = remindingDeliver;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public short getInvoice() {
		return invoice;
	}

	public void setInvoice(short invoice) {
		this.invoice = invoice;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	
    
    
}