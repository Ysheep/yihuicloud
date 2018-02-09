/**
 * 
 */
package com.qzdsoft.eshop.service.express.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.mapper.express.ExpressCompanyMapper;
import com.qzdsoft.eshop.service.express.ExpressCompanyService;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月26日
 * @see
 * @since 1.0
 */
@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {
	@Autowired
	private ExpressCompanyMapper expressCompanyMapper;
	
	@Override
	public List<TypeInfo> selectALLExpress() {
		return expressCompanyMapper.findAllExpress();
	}
	
	
}
