package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface UserSex {
	
	//状态：0 正常  1冻结
    static final String MAN_CODE = "0";
    static final String WOMEN_CODE = "1";
    
    static final TypeInfo MAN = new TypeInfo(MAN_CODE,"男");
    static final TypeInfo WOMEN = new TypeInfo(WOMEN_CODE,"女");
    
    static final List<TypeInfo> ALL = Arrays.asList(MAN,WOMEN);

}
