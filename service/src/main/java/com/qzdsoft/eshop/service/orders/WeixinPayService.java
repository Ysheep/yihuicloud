/**
 * 
 */
package com.qzdsoft.eshop.service.orders;

import java.util.Map;
import java.util.SortedMap;

import com.qzdsoft.vo.Response;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月28日
 * @see
 * @since 1.0
 */
public interface WeixinPayService
{
    
    Response<Map<Object,Object>> unifiedorder(Map<String,Object> params);
    
    Response<String> notify(SortedMap<String, String> backInfo);

}
