/**
 * 
 */
package com.qzdsoft.eshop.service.deliverytemplate;

import java.util.List;

import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateEditInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateInfo;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月19日
 * @see
 * @since 1.0
 */
public interface DeliveryTemplateService {
	/**
	 * 运费模板新增
	 * @param info
	 * @return
	 */
	Response tempalteSave(DeliveryTemplateEditInfo info);
	/**
	 * 获取已有的模板信息
	 * @return
	 */
	List<DeliveryTemplateInfo> getAllDeliveryTemplate();
	/**
	 * 删除运费模板
	 * @param templateId
	 * @return
	 */
	Response delTemplate(Integer templateId);
	
	List<TypeInfo> getAllDeliveryTemplates();
	/**
	 * 获取配送方式
	 * @param orderId
	 * @return
	 */
	List<TypeInfo> getDeliverysByOrderId(Integer orderId);
}
