/**
 * 
 */
package com.qzdsoft.eshop.service.express;

import java.util.Map;

import com.qzdsoft.eshop.vo.express.OrderExpressInfo;
import com.qzdsoft.vo.Response;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月26日
 * @see
 * @since 1.0
 */
public interface ExpressInfoService {
	/**
	 * 根据订单id查询物流信息
	 * @param orderId
	 * @return
	 */
	OrderExpressInfo selectOrderExpress(Integer orderId);
	
	Response<Map<String,Object>> getExpressInfo(Integer orderId);
	
	Map<String,Object> getExpressById(Integer orderId);
}
