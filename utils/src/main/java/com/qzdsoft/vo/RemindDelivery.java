package com.qzdsoft.vo;

public interface RemindDelivery {
	
	static final String NO_Remind_CODE = "0";
	static final String Remind_CODE = "1";
	
	static final TypeInfo NO_Remind = new TypeInfo(NO_Remind_CODE,"未提醒");
	static final TypeInfo Remind = new TypeInfo(Remind_CODE,"提醒");
}
