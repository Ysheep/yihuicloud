package com.qzdsoft.eshop.service.quartz;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.DictionaryInfo;
import com.qzdsoft.eshop.domain.orders.Orders;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.orders.OrdersMapper;
import com.qzdsoft.eshop.mapper.sys.DictionaryMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.express.ExpressInfoService;
import com.qzdsoft.eshop.vo.express.OrderExpressInfo;

@Configuration
@Component
@EnableScheduling
public class ScheduleJob {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger(ScheduleJob.class);  
	 
	 @Autowired
	 private OrdersMapper ordersMapper;
	 @Autowired
	 private ExpressInfoService expressInfoService;
	 @Autowired
	 private UserMapper userMapper;
	 @Autowired
	 private DictionaryMapper dictionaryMapper;
	 
	 @Transactional
	 public void execute() {
	    LOGGER.info("分润开始!!!");
	    BigDecimal first = new BigDecimal(0);
	    BigDecimal second = new BigDecimal(0);
	    List<DictionaryInfo> dictlist = dictionaryMapper.getDictionaryInfo("2");
	    for(DictionaryInfo dict:dictlist) {
	    	if(dict.getCode().equals("1级")) {
	    		first = new BigDecimal(dict.getValue());
	    	}
	    	if(dict.getCode().equals("2级")) {
	    		second = new BigDecimal(dict.getValue());
	    	}
	    }
	    List<Orders> orderList = ordersMapper.findAllOrders();
	    for(Orders orders:orderList) {
	    	if(orders.getStatus()!=5) {
	    		//未签收
	    		OrderExpressInfo info = expressInfoService.selectOrderExpress(orders.getOrderId());
	    		if(info.getDelivertTime()!=null) {
	    			//已发货
	    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    			long time = 0l;
	    			try {
						Date date1 = sdf.parse("2018-02-05 14:40:25");
						Date date2 = new Date();
						time = (date2.getTime() - date1.getTime())/1000/60/60/24;
					} catch (ParseException e) {
						LOGGER.error("分润计算时，时间格式化异常");
						e.printStackTrace();
					}
	    			if(time>4) {//发货后大于4天
	    				String expressState = expressInfoService.getExpressById(orders.getOrderId()).get("State").toString();
	    				if(expressState.equals("3")) {//已签收
	    					if(time>12) {//已签收并距离发货时间12天，自动收货，并参与分润
	    						orders.setStatus((short)5);
	    						try {
									this.calculate(orders,first,second);
								} catch (Exception e) {
									LOGGER.error("分润失败，订单号为{}"+orders.getOrderNo());
									e.printStackTrace();
								}
	    					}
	    				}
	    			}
	    		}
	    	}else {
	    		//已签收
	    		try {
					this.calculate(orders,first,second);
				} catch (Exception e) {
					LOGGER.error("分润失败，订单号为{}"+orders.getOrderNo());
					e.printStackTrace();
				}
	    	}
	    }
	    LOGGER.info("分润结束!!!");
	 }  	
	 
	 public void calculate(Orders order,BigDecimal first,BigDecimal second) throws Exception{
		 User user = userMapper.selectByPrimaryKey(order.getUserId()); //当前用户
		 if(user.getRefereeId()!=null&&!"".equals(user.getRefereeId())) {//有父级
			 User firstParent = new User();
			 firstParent.setPhone(user.getRefereeId());
			 firstParent = userMapper.selectOne(firstParent);
			 BigDecimal balance = firstParent.getBalance();//上级余额
			 BigDecimal payPrice = order.getPayPrice(); //支付金额
			 balance = balance.add(payPrice.multiply(first).setScale(2, BigDecimal.ROUND_HALF_UP));
			 firstParent.setBalance(balance);
			 userMapper.updateByPrimaryKeySelective(firstParent);//更新
			 if(firstParent.getRefereeId()!=null&&!"".equals(firstParent.getRefereeId())) {//父级的父级
				 User secondParent = new User();
				 secondParent.setPhone(user.getRefereeId());
				 secondParent = userMapper.selectOne(secondParent);
				 BigDecimal sedbalance = secondParent.getBalance();//上级余额
				 sedbalance = sedbalance.add(payPrice.multiply(first).setScale(2, BigDecimal.ROUND_HALF_UP));
				 secondParent.setBalance(sedbalance);
				 userMapper.updateByPrimaryKeySelective(secondParent);//更新
			 }
			 
		 }
		 order.setSplitStatus((short)1);//已分润
		 ordersMapper.updateByPrimaryKeySelective(order);
	 }
}
