package com.qzdsoft.eshop.mapper.express;

import com.qzdsoft.eshop.domain.express.ExpressInfo;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.express.OrderExpressInfo;

public interface ExpressInfoQueryMapper extends MyMapper<ExpressInfo> {
	
	OrderExpressInfo getOrderExpressInfo(Integer orderId);
}