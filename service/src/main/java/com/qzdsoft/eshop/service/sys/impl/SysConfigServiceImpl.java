/**
 * 
 */
package com.qzdsoft.eshop.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.domain.SysConfig;
import com.qzdsoft.eshop.mapper.sys.SysConfigMapper;
import com.qzdsoft.eshop.service.sys.SysConfigService;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月21日
 * @see
 * @since 1.0
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigMapper sysConfigMapper;
	/**
	 * @see com.qzdsoft.eshop.service.sys.SysConfigService#selectAll()
	 */
	@Override
	public List<SysConfig> selectAll() {
		// TODO Auto-generated method stub
		return sysConfigMapper.selectAll();
	}
	@Override
	public Map<String,String> selectCompanyInfo() {
		List<TypeInfo> info = sysConfigMapper.getCompanyInfo();
		Map<String,String> result = new HashMap<String,String>();
		for(TypeInfo in:info) {
			result.put(in.code, in.value);
		}
		return result;
	}
	
	
	
}
