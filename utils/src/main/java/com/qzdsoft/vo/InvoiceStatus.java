package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface InvoiceStatus {
	
	static final String NO_INVOICE_CODE = "0";
	static final String PERSONAL_INVOICE_CODE = "1";
	static final String COMPANY_INVOICE_CODE = "2";
	
	TypeInfo NO_INVOICE = new TypeInfo(NO_INVOICE_CODE,"不开发票");
	TypeInfo PERSONAL_INVOICE = new TypeInfo(PERSONAL_INVOICE_CODE,"个人发票");
	TypeInfo COMPANY_INVOICE = new TypeInfo(COMPANY_INVOICE_CODE,"单位发票");
	
	static final List<TypeInfo> ALL = Arrays.asList(NO_INVOICE,PERSONAL_INVOICE,COMPANY_INVOICE);
}
