package com.qzdsoft.eshop.service.express.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.mapper.express.ExpressInfoMapper;
import com.qzdsoft.eshop.mapper.express.ExpressInfoQueryMapper;
import com.qzdsoft.eshop.service.express.ExpressInfoService;
import com.qzdsoft.eshop.vo.express.OrderExpressInfo;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.entityUtil.KdniaoTrackQueryAPI;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;

@Service
public class ExpressInfoServiceImpl implements ExpressInfoService {
	private static final Logger logger = LoggerFactory.getLogger(ExpressInfoServiceImpl.class);
	@Autowired
	private ExpressInfoMapper expressInfoMapper;
	
	@Autowired
	private ExpressInfoQueryMapper expressInfoQueryMapper;
	
	@Override
	public OrderExpressInfo selectOrderExpress(Integer orderId) {
		OrderExpressInfo info = expressInfoQueryMapper.getOrderExpressInfo(orderId);
		return expressInfoQueryMapper.getOrderExpressInfo(orderId);
	}

	@Override
	public Response<Map<String,Object>> getExpressInfo(Integer orderId) {
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		OrderExpressInfo info = expressInfoQueryMapper.getOrderExpressInfo(orderId);
		if(info!=null) {
			try {
				String result = api.getOrderTracesByJson(info.getExpressCode(), info.getExpressNo()
						,SysConfigInfo.getValue(SysConfigCode.KDNiao_CODE),SysConfigInfo.getValue(SysConfigCode.KDNiao_API_KEY));
				logger.debug("result={}",result);
				Map<String,Object> map = JsonUtils.jsonToMap(result);
				map.put("ShipperName", info.getExpressName());
				return new Response<>(ResultEnum.SUCCESS,map);
			} catch (Exception e) {
				e.printStackTrace();
				return new Response<>(ResultEnum.ERROR,null);
			}
		}else{
			
		}
		return new Response<>(ResultEnum.ERROR,null);
	}

	@Override
	public Map<String, Object> getExpressById(Integer orderId) {
		OrderExpressInfo info = expressInfoQueryMapper.getOrderExpressInfo(orderId);
		Map<String, Object> result = new HashMap<String, Object>();
		if(info!=null && info.getExpressNo()!=null) {
			KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
			String express;
			try {
				express = api.getOrderTracesByJson(info.getExpressCode(), info.getExpressNo()
						,SysConfigInfo.getValue(SysConfigCode.KDNiao_CODE),SysConfigInfo.getValue(SysConfigCode.KDNiao_API_KEY));
				logger.info("rac={}",JsonUtils.jsonToMap(express));
				Map<String, Object> res = JsonUtils.jsonToMap(express);
				if(res.get("Success").toString().equals("false")) {
					result.put("traces", null);
				}else{
					result.put("traces", res.get("Traces"));
				}
				result.put("State", res.get("State").toString());
				return result;
			} catch (Exception e) {
				result.put("traces", null);
				e.printStackTrace();
				logger.error("快递鸟物流信息错误:={}",e.getMessage());
			}
		}else{
			result.put("traces", null);
			return result;
		}
		return result;
	}

	
	
}
