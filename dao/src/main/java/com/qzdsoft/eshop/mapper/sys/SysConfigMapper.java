package com.qzdsoft.eshop.mapper.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.SysConfig;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.vo.TypeInfo;

public interface SysConfigMapper extends MyMapper<SysConfig> {
	
	List<TypeInfo> getCompanyInfo();
}