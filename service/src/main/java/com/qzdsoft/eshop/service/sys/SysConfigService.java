package com.qzdsoft.eshop.service.sys;

import java.util.List;
import java.util.Map;

import com.qzdsoft.eshop.domain.SysConfig;

public interface SysConfigService {
	
	List<SysConfig> selectAll();
	/**
	 * 公司配置信息
	 * @return
	 */
	Map<String,String> selectCompanyInfo();
}
