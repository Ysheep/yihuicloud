package com.qzdsoft.eshop.ordersServiceTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qzdsoft.eshop.BaseTest;
import com.qzdsoft.eshop.domain.orders.GoodsOrder;
import com.qzdsoft.eshop.service.orders.OrdersService;
import com.qzdsoft.eshop.vo.orders.pc.GoodsOrderInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersQueryParam;
import com.qzdsoft.vo.Response;

public class OrderServiceTest extends BaseTest {
	
	@Autowired
	private OrdersService ordersService;
	
	@Test
	public void testOrderCreate(){
		OrderEditInfo info = new OrderEditInfo();
		info.setUserId(1);
		info.setOrderType(1);
		GoodsOrder good = new GoodsOrder();
		good.setNum(2);
		good.setSkuId(19);
		GoodsOrder good1 = new GoodsOrder();
		good1.setNum(27);
		good1.setSkuId(4);
		List<GoodsOrder> goods = new ArrayList<GoodsOrder>();
		goods.add(good);
		goods.add(good1);
//		info.setGoods(goods);
		Response<String> result = ordersService.orderAdd(info);
		System.out.println(result.getResult());
	}
	@Test
	public void testQueryByParam(){
		OrdersQueryParam param = new OrdersQueryParam();
		param.setUserId(1);
		param.setPage(0);
		param.setOrderStatus(0);
		ordersService.findOrderByParam(param);
	}
	
	
	@Test
	public void testtotalCarryprice() {
		List<GoodsOrderInfo> list = new ArrayList<>();
		GoodsOrderInfo info1 = new GoodsOrderInfo();
		info1.setNum(1);
		info1.setSkuId(19);//10 5
		info1.setGoodsId(3);
		GoodsOrderInfo info2 = new GoodsOrderInfo();
		info2.setNum(1);
		info2.setSkuId(18);
		info2.setGoodsId(61);
		GoodsOrderInfo info3 = new GoodsOrderInfo();
		info3.setNum(1);
		info3.setSkuId(6);//
		info3.setGoodsId(53);
		GoodsOrderInfo info4 = new GoodsOrderInfo();
		info4.setNum(1);
		info4.setSkuId(13);//10 5
		info4.setGoodsId(60);
		Integer cityid = 4524440;
		list.add(info1);
		list.add(info2);
		list.add(info3);
		list.add(info4);
		BigDecimal total = ordersService.totalCaryPrice(list, cityid);
		System.out.println(total);
	}
	
	public static void main(String[] args) {
//		List<GoodsOrderInfo> list = new ArrayList<>();
//		for(int i = 1;i<=3;i++) {
//			GoodsOrderInfo goods= new GoodsOrderInfo();
//			goods.setGoodsId(1);
//			goods.setSkuId(i+1);
//			goods.setNum(i+1);
//			list.add(goods);
//		}
//		GoodsOrderInfo goods1= new GoodsOrderInfo();
//		goods1.setGoodsId(2);
//		goods1.setSkuId(5);
//		goods1.setNum(3);
//		list.add(goods1);
//		
//		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
//		for(GoodsOrderInfo g:list) {
//			if(map.isEmpty()) {
//				map.put(g.getGoodsId(), g.getNum());
//			}else{
//				if(map.containsKey(g.getGoodsId())) {
//					Integer num = map.get(g.getGoodsId());
//					num+=g.getNum();
//					map.put(g.getGoodsId(), num);
//				}else{
//					map.put(g.getGoodsId(), g.getNum());
//				}
//			}
//		}
//		 Iterator<Integer> keys = map.keySet().iterator();
//		 while(keys.hasNext()){  
//			 	Integer key=keys.next();
//	            Integer num = map.get(key);  
//	           System.out.println("goodId="+key+"---"+"num="+num);
//	        }  
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try
	        {
	            Date payTime = sdf.parse("2018-02-08 15:49:35");
	            System.out.println(payTime);
	        }
	        catch (ParseException e)
	        {
	            e.printStackTrace();
	        }
	}
}
