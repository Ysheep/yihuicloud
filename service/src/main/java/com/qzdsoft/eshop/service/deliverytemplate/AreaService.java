package com.qzdsoft.eshop.service.deliverytemplate;

import java.util.List;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.vo.area.AreaModel;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月20日
 * @see
 * @since 1.0
 */
public interface AreaService {
	
	Area selectById(Integer areaId);
	
	/**
	 * 获取所有地址
	 * @return
	 */
	List<AreaModel> getAllarea();
	
	Response<List<TypeInfo>> findAreaById(Integer id);
}
