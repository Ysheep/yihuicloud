package com.qzdsoft.eshop.service.deliverytemplate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.domain.deliverytemplate.CarryMode;
import com.qzdsoft.eshop.domain.deliverytemplate.DeliveryTemplate;
import com.qzdsoft.eshop.mapper.deliverytemplate.AreaMapper;
import com.qzdsoft.eshop.mapper.deliverytemplate.CarryModeMapper;
import com.qzdsoft.eshop.mapper.deliverytemplate.DeliveryTemplateMapper;
import com.qzdsoft.eshop.mapper.deliverytemplate.DeliveryTemplateQueryMapper;
import com.qzdsoft.eshop.service.deliverytemplate.DeliveryTemplateService;
import com.qzdsoft.eshop.vo.deliverytemplate.CarryWayEditInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.CarryWayInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateEditInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateInfo;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.PricingMethod;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

import io.jsonwebtoken.lang.Collections;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月19日
 * @see
 * @since 1.0
 */
@Service
public class DeliveryTemplateServiceImpl implements DeliveryTemplateService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryTemplateServiceImpl.class);
	@Autowired
	private DeliveryTemplateMapper deliveryTemplateMapper;
	@Autowired
	private CarryModeMapper carryModeMapper;
	@Autowired
	private DeliveryTemplateQueryMapper deliveryTemplateQueryMapper;
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	@Transactional
	public Response tempalteSave(DeliveryTemplateEditInfo info) {
		List<CarryWayEditInfo> carryModels = info.getCarryModel();
		if(carryModels.size()<1) {
			Response.error("提交模板数据为空");
		}
		//模板
		DeliveryTemplate tem = new DeliveryTemplate();
		tem.setName(info.getName());
		tem.setAddr(info.getAddr());//宝贝地址
		tem.setPricingMethod(info.getPricingMethod());//计价方式
		tem.setFreeDelivery(info.getFreeDelivery());
		tem.setAddTime(new Date());
		tem.setIsInclPostageByif(Short.valueOf("0"));
		tem.setDeleteStatus(Short.valueOf("0"));
		deliveryTemplateMapper.insert(tem);
		//运送方式
		List<CarryMode> modes = new ArrayList<CarryMode>();
		for(CarryWayEditInfo cm:carryModels) {
			CarryMode mode = new CarryMode();
			mode.setTemplateId(tem.getTemplateId());
			mode.setFirstAmount(cm.getFirstAmount());
			mode.setSecondAmount(cm.getSecondAmount());
			mode.setIsDefault(cm.getIsDefault().shortValue());
			mode.setCarryWay(cm.getCarryWay());
			mode.setRegion(cm.getAreaId());
			
			switch (tem.getPricingMethod()) {
			case 1://计件
				mode.setFirstPiece(cm.getFirstSum().intValue());
				mode.setSecondPiece(cm.getSecondSum().intValue());
				break;
			case 2://计重
				mode.setFirstWeight(cm.getFirstSum());
				mode.setSecondWeight(cm.getSecondSum());
				break;
			}
			modes.add(mode);
		}
		if(!CollectionUtils.isEmpty(modes)) {
			carryModeMapper.insertList(modes);
		}
		return new Response(ResultEnum.SUCCESS);
	}

	@Override
	public List<DeliveryTemplateInfo> getAllDeliveryTemplate() {
		List<DeliveryTemplateInfo> info = deliveryTemplateQueryMapper.selectAll();
		if(!Collections.isEmpty(info)) {
			for(DeliveryTemplateInfo d:info) {
				List<CarryWayInfo> carrylist = d.getCarryWays();
				if(!Collections.isEmpty(carrylist)) {
					for(CarryWayInfo carry:carrylist) {
						String region = getArea(carry.getRegion());
						carry.setRegionList(region);
					}
				}
			}
		}
		return info;
	}
	
	public String getArea(String str){
		String strArea  = null;
		if(str.equals("0") || StringUtil.isEmpty(str)) {
			strArea  = "全国";
		}else{
			String[] regions = str.split(",");
			for(int i = 0; i<regions.length;i++) {
				Area area = areaMapper.selectByPrimaryKey(Integer.parseInt(regions[i]));
				if(area!=null) {
					if(strArea==null) {
						strArea = area.getName();
					}else{
						strArea = strArea+","+ area.getName();
					}
				}
			}
		}
		return strArea;
	}

	@Override
	public Response delTemplate(Integer templateId) {
		DeliveryTemplate template = deliveryTemplateMapper.selectByPrimaryKey(templateId);
		if(template==null) {
			logger.error("根据templateid 查询模板信息为空,删除模板失败");
			return Response.error("删除失败");
		}
		template.setDeleteStatus(Short.valueOf("1"));
		deliveryTemplateMapper.updateByPrimaryKeySelective(template);
		return new Response(ResultEnum.SUCCESS);
	}

	@Override
	public List<TypeInfo> getAllDeliveryTemplates() {
		// TODO Auto-generated method stub
		return deliveryTemplateQueryMapper.getAll();
	}

	@Override
	public List<TypeInfo> getDeliverysByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return deliveryTemplateQueryMapper.getCarryWayByOrderId(orderId);
	}
	
	
}
