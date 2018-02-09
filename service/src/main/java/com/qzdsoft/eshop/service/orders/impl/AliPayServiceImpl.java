package com.qzdsoft.eshop.service.orders.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qzdsoft.eshop.domain.log.WalletLog;
import com.qzdsoft.eshop.domain.orders.Orders;
import com.qzdsoft.eshop.domain.orders.PayLog;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.log.WalletLogMapper;
import com.qzdsoft.eshop.mapper.orders.OrdersMapper;
import com.qzdsoft.eshop.mapper.orders.PayLogMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.orders.AliPayService;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年2月7日
 * @see
 * @since 1.0
 */
@Service
public class AliPayServiceImpl implements AliPayService {
	private static final Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private PayLogMapper payLogMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private WalletLogMapper walletLogMapper;
	
	@Override
	@Transactional
	public Response<String> aliPayNotify(Map<String,String> param) {
		logger.debug("支付宝支付回调信息:param={}",param);
		
		 String orderNo = param.get("out_trade_no");
        if(StringUtil.isEmpty(orderNo)||StringUtil.isEmpty(param.get("trade_no"))
                ||StringUtil.isEmpty(param.get("total_amount"))
                ||StringUtil.isEmpty(param.get("trade_status")) || StringUtil.isEmpty(param.get("receipt_amount"))) {
            return new Response<>(ResultEnum.ERROR.getCode(),"参数错误");
        }
        PayLog log = new PayLog();
        log.setOrderNo(param.get("trade_no"));
        BigDecimal trandAmount = new BigDecimal(param.get("receipt_amount"));
        log.setPrice(trandAmount);
        log.setRespStatus(param.get("trade_status"));
        log.setRespTime(new Date());
        log.setMerOrderNo(orderNo);
        log.setRespInfo(JsonUtils.objectToJson(param));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date payTime = sdf.parse(param.get("gmt_payment"));
            log.setPayTime(payTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            logger.error("时间转换错误：{}",e);
        }
        payLogMapper.insert(log);
		if("TRADE_SUCCESS".equals(param.get("trade_status"))) {
			Orders record = new Orders();
			record.setOrderNo(orderNo);
			Orders order = ordersMapper.selectOne(record);
			if(order == null) {
				logger.error("根据userid,orderNo查询订单信息为空");
				logger.error("支付宝支付订单号不存在，订单号：{}",orderNo);
			}
			if(Short.parseShort(OrderStatus.PAID_CODE)==order.getStatus()) {
				logger.warn("该订单已经通知：orderNo:{},trade_no:{},",order.getOrderNo(),param.get("trade_no"));
				return new Response<>(ResultEnum.SUCCESS);
			}
			
			if (trandAmount.compareTo(order.getOnlinePayMoney()) != 0)
			{
				logger.error("微信支付订单和实际支付订单金额不符合，订单金额：{}，支付回调金额：{}",order.getPayPrice(), trandAmount);
			}else{
				if(order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE) || order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE)) {
                	User user = userMapper.selectByPrimaryKey(order.getUserId());
                	if(user == null) {
                		logger.error("根据用户id查询用户信息为空,userid={},orderNo={}",order.getUserId(),order.getOrderNo());
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
			return new Response<>(ResultEnum.SUCCESS);
		}else{
			return new Response<>(ResultEnum.ERROR);
		}
	}
	
	
}
