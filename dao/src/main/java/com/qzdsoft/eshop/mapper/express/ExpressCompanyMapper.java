package com.qzdsoft.eshop.mapper.express;

import java.util.List;

import com.qzdsoft.eshop.domain.express.ExpressCompany;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.vo.TypeInfo;

public interface ExpressCompanyMapper extends MyMapper<ExpressCompany> {
	
	List<TypeInfo> findAllExpress();
}