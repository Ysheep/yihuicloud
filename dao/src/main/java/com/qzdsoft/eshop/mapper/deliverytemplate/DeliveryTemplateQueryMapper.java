package com.qzdsoft.eshop.mapper.deliverytemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.deliverytemplate.CarryPriceInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.TemlateCarryModelInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.TemplateSkuInfo;
import com.qzdsoft.vo.TypeInfo;

public interface DeliveryTemplateQueryMapper {
	
	List<DeliveryTemplateInfo> selectAll();
	
	List<TypeInfo> getAll();
	
	List<TemplateSkuInfo> selectBySkuid(@Param("orderId")Integer orderId,@Param("carryType")Integer carryType);
	
	List<TypeInfo> getCarryWayByOrderId(Integer orderId);
	
	TemlateCarryModelInfo selectByGoodsId(@Param("goodsId")Integer goodsId);
}