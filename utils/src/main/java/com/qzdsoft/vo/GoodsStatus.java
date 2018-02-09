package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface GoodsStatus {
	
    static final String USE_CODE = "0";
    static final String DELET_CODE = "1";
    
    static final TypeInfo USE = new TypeInfo(USE_CODE,"正常");
    static final TypeInfo DELET = new TypeInfo(DELET_CODE,"删除");
    
    static final List<TypeInfo> ALL = Arrays.asList(USE,DELET);

}
