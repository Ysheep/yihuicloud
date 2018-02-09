package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface RefundStatus {
	
	static final String REFUSE_CODE="0";//拒绝
	static final String APPROVE_CODE="1";//同意
	
	static final TypeInfo REFUSE = new TypeInfo(REFUSE_CODE,"拒绝");
	static final TypeInfo APPROVE = new TypeInfo(APPROVE_CODE,"同意");
	
	static final List<TypeInfo> ALL = Arrays.asList(REFUSE,APPROVE);
}
