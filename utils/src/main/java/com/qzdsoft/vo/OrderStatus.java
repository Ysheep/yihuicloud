/**
 * 
 */
package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月25日
 * @see
 * @since 1.0
 */
public interface OrderStatus {
	
	static final String UNPAID_CODE = "0";
	static final String PAID_CODE = "1";
	static final String REFUNDING_CODE = "2";
	static final String REFUNDED_CODE = "3";
	static final String DELIVERED_CODE = "4";
	static final String SIGNED_CODE = "5";
	static final String REFUSE_CODE = "6";
	static final String CLOSE_CODE = "7";
	
	static final TypeInfo UNPAID = new TypeInfo(UNPAID_CODE,"待付款");
	static final TypeInfo PAID = new TypeInfo(PAID_CODE,"付款成功");
	static final TypeInfo REFUNDING = new TypeInfo(REFUNDING_CODE,"退款中");
	static final TypeInfo REFUNDED = new TypeInfo(REFUNDED_CODE,"退款成功");
	static final TypeInfo DELIVERED = new TypeInfo(DELIVERED_CODE,"已发货");
	static final TypeInfo SIGNED = new TypeInfo(SIGNED_CODE,"已签收");
	static final TypeInfo REFUSE = new TypeInfo(REFUSE_CODE,"拒绝退款");
	static final TypeInfo CLOSE = new TypeInfo(CLOSE_CODE,"交易关闭");

	static final List<TypeInfo> ALL = Arrays.asList(UNPAID,PAID,REFUNDING,REFUNDED,DELIVERED,SIGNED,REFUSE,CLOSE);
}
