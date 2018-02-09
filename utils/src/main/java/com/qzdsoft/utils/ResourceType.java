package com.qzdsoft.utils;

import java.util.Arrays;
import java.util.List;

import com.qzdsoft.vo.TypeInfo;

public interface ResourceType {
	//状态：0 目录菜单  1功能菜单
    static final String DIRECTORIES_MENU = "0";
    static final String FUNCTION_MENU = "1";
    
    static final TypeInfo DIRECTORIES = new TypeInfo(DIRECTORIES_MENU,"目录菜单");
    static final TypeInfo FUNCTION= new TypeInfo(FUNCTION_MENU,"功能菜单");
    
    static final List<TypeInfo> ALL = Arrays.asList(DIRECTORIES,FUNCTION);
}
