/**
 * 
 */
package com.qzdsoft.eshop.service.orders.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.log.WalletLog;
import com.qzdsoft.eshop.domain.orders.Orders;
import com.qzdsoft.eshop.domain.orders.PayLog;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.log.WalletLogMapper;
import com.qzdsoft.eshop.mapper.orders.OrdersMapper;
import com.qzdsoft.eshop.mapper.orders.PayLogMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.orders.WeixinPayService;
import com.qzdsoft.eshop.service.orders.impl.utils.PayCommonUtil;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.utils.httpUtil.WebUtils;
import com.qzdsoft.utils.xml.XmlUtil;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.PayStatus;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月28日
 * @see
 * @since 1.0
 */
@Service
public class WeixinPayServiceImpl implements WeixinPayService
{

    private static final Logger logger = LoggerFactory.getLogger(WeixinPayServiceImpl.class);
 
    
    @Autowired
    private OrdersMapper ordersMapper;
    
    @Autowired
    private PayLogMapper payLogMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private WalletLogMapper walletLogMapper;
    
    
    /**
     * 
     * @see com.qzdsoft.eshop.service.pay.WeixinPayService#unifiedorder(java.util.Map)
     */
    public Response<Map<Object,Object>> unifiedorder(Map<String,Object> params)
    {
        SortedMap<Object, Object> parameterMap = new TreeMap<Object, Object>();
        parameterMap.put("appid", SysConfigInfo.getValue(SysConfigCode.APPID));  
        parameterMap.put("mch_id", SysConfigInfo.getValue(SysConfigCode.MCH_ID));  
        parameterMap.put("nonce_str", PayCommonUtil.getRandomString(32));  
        parameterMap.put("body", params.get("desc"));  
        parameterMap.put("out_trade_no", params.get("orderNo"));  
        parameterMap.put("fee_type", "CNY");
        BigDecimal totalAmount = (BigDecimal)params.get("totalAmount");
        BigDecimal total = totalAmount.multiply(new BigDecimal(100));  
        java.text.DecimalFormat df=new java.text.DecimalFormat("0");  
        parameterMap.put("total_fee", df.format(total));  
        parameterMap.put("spbill_create_ip", params.get("ip"));  
        parameterMap.put("notify_url", SysConfigInfo.getValue(SysConfigCode.WX_NOTIFY));  
        parameterMap.put("trade_type", "NATIVE");
//        logger.info("signbefort={}",parameterMap.);
        String sign = PayCommonUtil.getSign(parameterMap);
        logger.info("signafter={}",sign);
        parameterMap.put("sign", sign);  
        String requestXML = XmlUtil.getRequestXml(parameterMap);
        logger.debug("wx统一下单requestXml：{}",requestXML);
        
        String result = WebUtils.post("https://api.mch.weixin.qq.com/pay/unifiedorder",requestXML,"utf-8");  
        logger.debug("wx统一下单返回：{}",result);
        
        Map<String,String> resultMap = XmlUtil.xml2Map(result);
        logger.debug("wx统一下单返回：{}",resultMap);
        
        String prepayId = null;
        if("SUCCESS".equals(resultMap.get("return_code"))) {
            prepayId = resultMap.get("prepay_id");
            String codeUrl = resultMap.get("code_url");
            long timeStamp = System.currentTimeMillis()/1000;
            String nonceStr = PayCommonUtil.getRandomString(32);
            String packagewx = "prepay_id="+prepayId;
            String signType ="MD5";
            SortedMap<Object,Object> map = new TreeMap<>();
            map.put("appId", SysConfigInfo.getValue(SysConfigCode.APPID));
            map.put("timeStamp", timeStamp+"");
            map.put("nonceStr", nonceStr);
            map.put("package", packagewx);
            map.put("signType", signType);
            String paySign = PayCommonUtil.getSign(map);
            map.put("paySign", paySign);
            map.put("codeUrl", codeUrl);
            logger.debug("wx统一下单prepayId：{},codeUrl:{}",prepayId,codeUrl);
            return new Response<Map<Object,Object>>(ResultEnum.SUCCESS,map);
        }
        return new Response<>(ResultEnum.ERROR);
    }
  
    /**
     * 
     * @see com.qzdsoft.eshop.service.pay.WeixinPayService#notify(java.util.SortedMap)
     */
    @Transactional
    public Response<String> notify(SortedMap<String, String> backInfo)
    {
        
        logger.info("wx支付回调信息：{}",backInfo);
        String orderNo = backInfo.get("out_trade_no");
        if(StringUtil.isEmpty(orderNo)||StringUtil.isEmpty(backInfo.get("transaction_id"))
                ||StringUtil.isEmpty(backInfo.get("total_fee"))
                ||StringUtil.isEmpty(backInfo.get("return_code"))) {
            return new Response<>(ResultEnum.ERROR.getCode(),"参数错误");
        }
        
        PayLog log = new PayLog();
        log.setOrderNo(backInfo.get("transaction_id"));
        BigDecimal trandAmount = new BigDecimal(backInfo.get("total_fee")).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
        log.setPrice(trandAmount);
        log.setRespStatus(backInfo.get("trade_result"));
        log.setRespTime(new Date());
       
        log.setMerOrderNo(orderNo);
        log.setRespInfo(JsonUtils.objectToJson(backInfo));
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            Date payTime = sdf.parse(backInfo.get("time_end"));
            log.setPayTime(payTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            logger.error("时间转换错误：{}",e);
        }
          
        payLogMapper.insert(log);
        
        if(!PayCommonUtil.isTenpaySign(backInfo,SysConfigInfo.getValue(SysConfigCode.API_KEY))) { 
            logger.error("签名错误，回调信息：{}",backInfo);
            return new Response<>(ResultEnum.ERROR);
        }
        if("SUCCESS".equals(backInfo.get("return_code"))) {
            
            Orders record = new Orders();
            record.setOrderNo(orderNo);
            Orders order = ordersMapper.selectOne(record);
            logger.debug("微信支付回调，订单信息：{}",order);
            if (order == null)
            {
                logger.error("微信支付订单号不存在，订单号：{}",orderNo);
            }
            if(Short.parseShort(OrderStatus.PAID_CODE)==order.getStatus()) {
                logger.warn("该订单已经通知：{}",backInfo);
                return new Response<>(ResultEnum.SUCCESS); 
            }
            else
            {
                if (!trandAmount.equals(order.getPayPrice()))
                {
                    logger.error("微信支付订单和实际支付订单金额不符合，订单金额：{}，支付回调金额：{}",order.getPayPrice(), trandAmount);
                }
                else
                {
                	if(order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE) || order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE)) {
                    	User user = userMapper.selectByPrimaryKey(order.getUserId());
                    	if(user == null) {
                    		logger.error("根据userid查询用户信息不存在，orderNo:{},userid:{}",order.getUserId(),order.getUserId());
                    	}else{
                    		if(user.getBalance().compareTo(order.getBalancePayMoney()) == -1) {
                    			logger.error("用户的余额不足，支付失败，userid={},orderid={}",user.getUserId(),order.getOrderId());
                    		}else{
                    			BigDecimal userBlance = user.getBalance().subtract(order.getBalancePayMoney());
                    			user.setBalance(userBlance);
                    			userMapper.updateByPrimaryKeySelective(user);
                    			WalletLog wallet = new WalletLog();
                    			wallet.setUserId(user.getUserId());
                    			wallet.setPayTime(new Date());
                    			wallet.setRemark("余额支付");
                    			wallet.setOrderId(order.getOrderId());
                    			wallet.setSplitMoney(order.getBalancePayMoney());
                    			wallet.setType(1);//购买支出
                    			walletLogMapper.insert(wallet);
                    			order.setStatus(Short.parseShort(OrderStatus.PAID_CODE));
                    		}
                    	}
                    }else{
                    	order.setStatus(Short.parseShort(OrderStatus.PAID_CODE));
                    }
                    ordersMapper.updateByPrimaryKey(order);
                    
                }
            }
            
        }else {
            logger.error("支付失败,返回结果：{}",backInfo);
            return new Response<>(ResultEnum.ERROR);
        }
        
        return new Response<>(ResultEnum.SUCCESS);
    }

}
