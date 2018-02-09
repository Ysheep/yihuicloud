package com.qzdsoft.eshop.controller.pc;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.internal.util.AlipaySignature;
import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.service.orders.AliPayService;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;

@RequestMapping("/alipay")
@Controller
public class AliPayController  extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);
	
	@Autowired
	private AliPayService aliPayService;
	
	@RequestMapping("/alipay_notify")
	public void callBack(){
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		
		PrintWriter writer = null;
		try {
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			 writer = response.getWriter();
			 //调用SDK验证签名
			 logger.debug("支付宝支付回调map:{}",params);
			 boolean flag = AlipaySignature.rsaCheckV1(params,
					SysConfigInfo.getValue(SysConfigCode.ALIPAY_PUBLIC_KEY),
					SysConfigInfo.getValue(SysConfigCode.ALIPAY_CHARSET), 
					SysConfigInfo.getValue(SysConfigCode.ALIPAY_SIGN_TYPE));
			 logger.debug("验证签名flag:{}",flag);
			if(flag) {
				if("TRADE_FINISHED".equals(params.get("trade_status"))) {
					 writer.println("success");
				}else if("TRADE_SUCCESS".equals(params.get("trade_status"))) {
					Response<String> result = aliPayService.aliPayNotify(params);
					if(result.getCode() == ResultEnum.SUCCESS.getCode()) {
						writer.println("success");
						writer.flush();
					}else{
						logger.error("支付失败");
						writer.println("fail");
						writer.flush();
					}
				}
			}else{
				logger.error("验证失败");
				writer.println("fail");
				writer.flush();
			}
			 
		} catch (Exception e) {
			logger.error("支付宝支付通知错误", ExceptionUtil.getStackTrace(e));
			e.printStackTrace();
			writer.println("fail");
			writer.flush();
		}
	}
	
	@RequestMapping("/return_url")
	public ModelAndView aliPayReturn() {
		 logger.debug("支付宝支付同步通知返回 ");
		 ModelAndView mv = new ModelAndView("redirect:/user/orders/my-orders");
		 
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		 
		 try {
				for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					//乱码解决，这段代码在出现乱码时使用
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
					params.put(name, valueStr);
				}
				 //调用SDK验证签名
				 logger.debug("支付宝支付同步通知返回  map:{}",params);
				 boolean flag = AlipaySignature.rsaCheckV1(params,
						SysConfigInfo.getValue(SysConfigCode.ALIPAY_PUBLIC_KEY),
						SysConfigInfo.getValue(SysConfigCode.ALIPAY_CHARSET), 
						SysConfigInfo.getValue(SysConfigCode.ALIPAY_SIGN_TYPE));
				 logger.debug("验证签名flag:{}",flag);
				if(flag) {
					logger.debug("支付宝支付同步通知返回 orderNo={}",params.get("trade_no"));
					
				}else{
					logger.error("支付宝支付同步通知返回 验证失败");
				}
			} catch (Exception e) {
				logger.error("支付宝支付同步通知返回 错误", ExceptionUtil.getStackTrace(e));
				e.printStackTrace();
			}
		return mv;
	}
}
