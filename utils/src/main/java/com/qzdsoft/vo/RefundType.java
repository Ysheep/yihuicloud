package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface RefundType {
	
	static final  String REFUND_CODE = "0";//退款
	static final  String REFUND_GOODS_CODE = "1";//退款退货
	
	static final TypeInfo REFUND = new TypeInfo(REFUND_CODE,"退款");
	static final TypeInfo REFUND_GOODS = new TypeInfo(REFUND_GOODS_CODE,"退款退货");
	
	static final List<TypeInfo> ALL = Arrays.asList(REFUND,REFUND_GOODS);
}
