package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface PayType {
	
	static final String WXPAY_CODE = "0";//微信
	static final String ALIPAY_CODE = "1";//支付宝
	static final String WXPAY_BALANCE_CODE = "2";//微信+余额 
	static final String ALIPAY_BALANCE_CODE = "3";//支付宝+余额;
	 
	static final TypeInfo WXPAY = new TypeInfo(WXPAY_CODE,"微信");
	static final TypeInfo ALIPAY = new TypeInfo(ALIPAY_CODE,"支付宝");
	
	static final TypeInfo WXPAY_BALANCE = new TypeInfo(WXPAY_BALANCE_CODE,"微信+余额");
	static final TypeInfo ALIPAY_BALANCE = new TypeInfo(ALIPAY_BALANCE_CODE,"支付宝+余额");
	 
	 static final List<TypeInfo> ALL = Arrays.asList(WXPAY,ALIPAY);
}
