package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface CompanyNoticeCode {
	
	static final String STORY_CODE = "0";
	static final String QIANLI_CODE = "1";
	static final String JOIN_CODE = "2";
	static final String TOUCH_CODE = "3";
	
	static final TypeInfo STORY = new TypeInfo(STORY_CODE,"品牌故事");
	static final TypeInfo QIANLI = new TypeInfo(QIANLI_CODE,"千里寻马");
	static final TypeInfo JOIN = new TypeInfo(JOIN_CODE,"加盟我们");
	static final TypeInfo TOUCH = new TypeInfo(TOUCH_CODE,"联系我们");
	
	static final List<TypeInfo> ALL = Arrays.asList(STORY,QIANLI,JOIN,TOUCH);
}
