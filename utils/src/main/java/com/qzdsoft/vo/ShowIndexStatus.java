package com.qzdsoft.vo;

import java.util.Arrays;
import java.util.List;

public interface ShowIndexStatus {
	
	 	String NOT_SHOW_CODE = "0";
	    String IS_SHOW_CODE = "1";
	    TypeInfo NOT_SHOW = new TypeInfo(NOT_SHOW_CODE,"不显示");
	    TypeInfo IS_SHOW = new TypeInfo(IS_SHOW_CODE,"显示");
	    
	    static final List<TypeInfo> ALL = Arrays.asList(NOT_SHOW,IS_SHOW);
	    
	    
}	
