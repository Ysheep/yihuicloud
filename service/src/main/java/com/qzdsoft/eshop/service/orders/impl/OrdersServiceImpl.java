/**
 * 
 */
package com.qzdsoft.eshop.service.orders.impl;

import java.math.BigDecimal;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzdsoft.eshop.domain.deliverytemplate.DeliveryTemplate;
import com.qzdsoft.eshop.domain.express.ExpressCompany;
import com.qzdsoft.eshop.domain.express.ExpressInfo;
import com.qzdsoft.eshop.domain.goods.Comment;
import com.qzdsoft.eshop.domain.goods.CommentImg;
import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.domain.goods.ShoppingCart;
import com.qzdsoft.eshop.domain.orders.GoodsOrder;
import com.qzdsoft.eshop.domain.orders.Orders;
import com.qzdsoft.eshop.domain.orders.Refund;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.domain.user.UserAddress;
import com.qzdsoft.eshop.mapper.deliverytemplate.DeliveryTemplateMapper;
import com.qzdsoft.eshop.mapper.deliverytemplate.DeliveryTemplateQueryMapper;
import com.qzdsoft.eshop.mapper.express.ExpressCompanyMapper;
import com.qzdsoft.eshop.mapper.express.ExpressInfoMapper;
import com.qzdsoft.eshop.mapper.express.ExpressInfoQueryMapper;
import com.qzdsoft.eshop.mapper.goods.CommentImgMapper;
import com.qzdsoft.eshop.mapper.goods.CommentMapper;
import com.qzdsoft.eshop.mapper.goods.GoodsSkuMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesSpecMapper;
import com.qzdsoft.eshop.mapper.orders.GoodsOrderMapper;
import com.qzdsoft.eshop.mapper.orders.OrdersMapper;
import com.qzdsoft.eshop.mapper.orders.OrdersQueryMapper;
import com.qzdsoft.eshop.mapper.orders.RefundMapper;
import com.qzdsoft.eshop.mapper.shoppingcart.ShoppingCartMapper;
import com.qzdsoft.eshop.mapper.user.UserAddressMapper;
import com.qzdsoft.eshop.mapper.user.UserAddressQueryMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.orders.OrdersService;
import com.qzdsoft.eshop.service.orders.WeixinPayService;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.CarryWayInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.TemlateCarryModelInfo;
import com.qzdsoft.eshop.vo.orders.CommentsEditInfo;
import com.qzdsoft.eshop.vo.orders.CommentsInfo;
import com.qzdsoft.eshop.vo.orders.OrderAddressInfo;
import com.qzdsoft.eshop.vo.orders.OrderDetailInfo;
import com.qzdsoft.eshop.vo.orders.OrderGoodsItem;
import com.qzdsoft.eshop.vo.orders.OrdersQueryInfo;
import com.qzdsoft.eshop.vo.orders.pc.GoodsOrderInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderExpressInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderPayInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderRefundEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderSubmitInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersDetailInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersQueryParam;
import com.qzdsoft.utils.DateUtil;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.utils.WebToolUtils;
import com.qzdsoft.vo.DeleteStatus;
import com.qzdsoft.vo.IsUseBalance;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.RemindDelivery;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SplitStatus;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年2月1日
 * @see
 * @since 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrdersQueryMapper ordersQueryMapper;
	
	@Autowired
	private GoodsPropertiesSpecMapper goodsPropertiesSpecMapper;
	
	@Autowired
	private GoodsSkuMapper goodsSkuMapper;
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GoodsOrderMapper  goodsOrderMapper;
	
	@Autowired
	private RefundMapper refundMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;
    
    @Autowired
    private ExpressInfoMapper expressInfoMapper;
    
    @Autowired
    private ExpressInfoQueryMapper expressInfoQueryMapper;
    
    @Autowired
    private UserAddressQueryMapper userAddressQueryMapper;
    
    @Autowired
	private DeliveryTemplateQueryMapper deliveryTemplateQueryMapper;
    
    @Autowired
    private DeliveryTemplateMapper deliveryTemplateMapper;
    
    @Autowired
    private WeixinPayService weixinPayService;
    
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentImgMapper commentImgMapper;
	
	@Override
	public Page<OrdersInfo> findOrderByParam(OrdersQueryParam param) {
		PageHelper.startPage(param.getPage(), Page.PAGESIZE);
		List<OrdersInfo> orderslist = ordersQueryMapper.findByParam(param);
		for(OrdersInfo order:orderslist) {
			order.setGoodsNum(order.getItems().size());
			BigDecimal goodsTotalAmount =  BigDecimal.ZERO;
			for(GoodsOrderInfo good:order.getItems()){
				BigDecimal goodTotal =good.getActualTotal();
				goodsTotalAmount = goodsTotalAmount.add(goodTotal);
			}
			order.setActualTotal(goodsTotalAmount);
		}
		PageInfo<OrdersInfo> pageInfo = new PageInfo<OrdersInfo>(orderslist);
		Page<OrdersInfo> page = new Page<OrdersInfo>(Page.PAGESIZE, pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal(), orderslist);
		return page;
	}

	@Override
	public OrdersDetailInfo findOrderById(Integer userId, Integer orderId) {
		OrdersDetailInfo info = ordersQueryMapper.findOrderById(userId, orderId);
		//计算商品总价
		if(info!=null) {
			BigDecimal goodsTotalAmount =  BigDecimal.ZERO;
			List<GoodsOrderInfo> goods = info.getItems();
			for(GoodsOrderInfo good:goods) {
				BigDecimal goodTotal =good.getActualTotal();//good.getPrice().multiply(new BigDecimal(good.getNum()));
				goodsTotalAmount = goodsTotalAmount.add(goodTotal);
				
				if(good.getSpecs()!=null && !"".equals(good.getSpecs())) {
					String[] specId = good.getSpecs().split(",");
					StringBuffer sb = new StringBuffer();
					int i = 0;
					for(String s : specId) {
						String name = goodsPropertiesSpecMapper.selectByPrimaryKey(Integer.parseInt(s.split(":")[1])).getName();
						if(i ==0) {
							sb.append(name);
							i++;
						}else {
							sb.append(","+name);
						}
					}
					good.setSpecs(sb.toString());
				}
			}
			info.setTotalPrice(goodsTotalAmount.setScale(2, BigDecimal.ROUND_UP));
			info.setGoodsNum(goods.size());
			info.setActualTotal(info.getTotalPrice().add(info.getEndCarrige()!=null?info.getEndCarrige():BigDecimal.ZERO));
		}
		return info;
	}

	@Override
	@Transactional
	public Response<String> orderAdd(OrderEditInfo info) {
		Orders nOrder = new Orders();
		AddressInfo defaultAdd = userAddressQueryMapper.selectDefaultAddress(info.getUserId());
		if(defaultAdd !=null) {
			nOrder.setAddressId(defaultAdd.getAddressId());
			nOrder.setPhone(defaultAdd.getMobile());
			nOrder.setTrueName(defaultAdd.getLinkMan());
			nOrder.setAddressInfo(defaultAdd.getProvinceStr()+defaultAdd.getCityStr()+defaultAdd.getDistrictStr());
		}
		nOrder.setStatus(Short.valueOf(OrderStatus.UNPAID_CODE));
		nOrder.setDeleteStatus(DeleteStatus.USEING.shortValue());
		nOrder.setSplitStatus(Short.valueOf(SplitStatus.NOT_SPLIT_CODE));
		nOrder.setUserId(info.getUserId());
		nOrder.setCtime(new Date());
		nOrder.setRemindingDeliver(Short.valueOf("0"));
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        String millTime = sdf.format(new Date());
        String orderNo = "";
        orderNo = millTime+new Random().nextInt(1000000);
		nOrder.setOrderNo(orderNo);
		//1.查询商品。计算价格
		List<GoodsOrderInfo> goods = info.getGoods();
		if(goods.size() == 0) {
			logger.error("订单创建失败，提交的商品数据为空");
			return Response.error("提交的商品数据为空");
		}
		BigDecimal goodsTotalPrice = BigDecimal.ZERO;
		List<GoodsOrder> goodsOrderList = new ArrayList<GoodsOrder>();
		for(GoodsOrderInfo good:goods) {
			GoodsOrder newGoodsOrder = new GoodsOrder();
			GoodsSku sku = goodsSkuMapper.selectByPrimaryKey(good.getSkuId());
			if(sku == null) {//
				logger.error("根据id查询商品信息为空,skuid={}",good.getSkuId());
				return Response.error("提交的商品已失效，请重新提交");
			}
			BigDecimal goodAmount = sku.getPrice().multiply(new BigDecimal(good.getNum()));//商品应收总额
			goodsTotalPrice = goodsTotalPrice.add(goodAmount);
			
			newGoodsOrder.setSkuId(good.getSkuId());
			newGoodsOrder.setNum(good.getNum());
			newGoodsOrder.setPrice(sku.getPrice());
			newGoodsOrder.setTotalMoney(goodAmount);
			newGoodsOrder.setEndPayMoney(goodAmount);
			goodsOrderList.add(newGoodsOrder);
		}
		nOrder.setPayPrice(goodsTotalPrice);
		//计算运费
		BigDecimal carryPrice = totalCaryPrice(info.getGoods(),defaultAdd!=null?defaultAdd.getCityId():null);
		nOrder.setCarrige(carryPrice);
		nOrder.setEndCarrige(carryPrice);
		int orderinsert = ordersMapper.insert(nOrder);
		logger.error("orderId={}",nOrder.getOrderId());
		if(orderinsert!=1) {
			logger.error("创建订单失败，插入的条数不正确，insertcount={}",orderinsert);
			throw new RuntimeException("订单创建失败");
		}
		for(GoodsOrder good:goodsOrderList) {
			good.setOrderId(nOrder.getOrderId());
		}
		int count = goodsOrderMapper.insertList(goodsOrderList);
		if(count!=goods.size()) {
			logger.error("订单创建失败，插入goodsOrder条数不正确，insertcount={},size={}",count,goods.size());
			throw new RuntimeException("订单创建失败");
		}
	   //删除购物车的商品
       if(info.getOrderType() == 1) {
    	   for(GoodsOrder good:goodsOrderList) {
    		   ShoppingCart record = new ShoppingCart();
    		   record.setUserId(info.getUserId());
    		   record.setSkuId(good.getSkuId());
    		   record.setDeleteStatus(DeleteStatus.USEING.shortValue());
    		   ShoppingCart cart = shoppingCartMapper.selectOne(record);
    		   cart.setDeleteStatus(DeleteStatus.DELETE.shortValue());
    		   shoppingCartMapper.updateByPrimaryKeySelective(cart);
    	   }
       }
	  return new Response<>(ResultEnum.SUCCESS,nOrder.getOrderId().toString());
	}

	@Override
	@Transactional
	public Response<String> cancelOfOrder(Integer orderId, Integer userId) {
//		Orders record = new Orders();
//		record.setOrderId(orderId);
//		record.setUserId(userId);
//		record.setDeleteStatus(DeleteStatus.USEING.shortValue());;
//		Orders order = ordersMapper.selectOne(record);
		Orders order = ordersMapper.selectByOrderId(orderId,userId);
		if(order == null) {
			logger.error("根据orderID，userid查询订单信息为空，userid={},orderid={}",userId,orderId);
			return Response.error("订单信息不存在，请刷新页面重新操作");
		}
		if(order.getStatus() != Integer.parseInt(OrderStatus.UNPAID_CODE)) {
			logger.error("订单已付款，不能取消订单");
			return Response.error("订单已支付,不能取消订单");
		}
		order.setStatus(Short.valueOf(OrderStatus.CLOSE_CODE));
		int upcount = ordersMapper.updateByPrimaryKey(order);
		if(upcount != 1) {
			logger.error("更新订单失败,更新条数不正确，upcount={},orderId={}",upcount,orderId);
			throw new RuntimeException("取消订单失败，更新条数不正确");
		}
		return Response.success("取消成功");
	}

	@Override
	@Transactional
	public Response<String> remindDeliver(Integer orderId, Integer userId) {
//		Orders record = new Orders();
//		record.setOrderId(orderId);
//		record.setUserId(userId);
//		record.setDeleteStatus(DeleteStatus.USEING.shortValue());;
//		Orders order = ordersMapper.selectOne(record);
		Orders order = ordersMapper.selectByOrderId(orderId,userId);
		if(order == null) {
			logger.error("根据orderID，userid查询订单信息为空，userid={},orderid={}",userId,orderId);
			return Response.error("订单信息不存在，请刷新页面重新操作");
		}
		if(order.getStatus() != Integer.parseInt(OrderStatus.PAID_CODE)) {
			logger.error("订单还支付，请前去支付。orderid={}",orderId);
			return Response.error("订单还支付,请前去支付");
		}
		order.setRemindingDeliver(Short.valueOf(RemindDelivery.Remind_CODE));
		int upcount = ordersMapper.updateByPrimaryKey(order);
		if(upcount != 1) {
			logger.error("更新订单失败,更新条数不正确，upcount={},orderId={}",upcount,orderId);
			throw new RuntimeException("发货提醒失败，更新条数不正确");
		}
		return Response.success("提醒成功");
	}

	@Override
	public Response<String> signedOrder(Integer orderId, Integer userId) {
//		Orders record = new Orders();
//		record.setOrderId(orderId);
//		record.setUserId(userId);
//		record.setDeleteStatus(DeleteStatus.USEING.shortValue());;
//		Orders order = ordersMapper.selectOne(record);
		Orders order = ordersMapper.selectByOrderId(orderId,userId);
		if(order == null) {
			logger.error("根据orderID，userid查询订单信息为空，userid={},orderid={}",userId,orderId);
			return Response.error("订单信息不存在，请刷新页面重新操作");
		}
		if(order.getStatus() != Integer.parseInt(OrderStatus.DELIVERED_CODE)) {
			logger.error("订单还未发货，请确定订单信息。orderid={}",orderId);
			return Response.error("订单还未发货,不能确认收货");
		}
		order.setStatus(Short.valueOf(OrderStatus.SIGNED_CODE));
		order.setEndTime(new Date());
		int upcount = ordersMapper.updateByPrimaryKey(order);
		if(upcount != 1) {
			logger.error("更新订单失败,更新条数不正确，upcount={},orderId={}",upcount,orderId);
			throw new RuntimeException("确认收货失败，更新条数不正确");
		}
		return Response.success("确认收货成功!");
	}

	@Override
	@Transactional
	public Response<String> applyRefund(OrderRefundEditInfo info) {
		Orders record = new Orders();
		record.setOrderId(info.getOrderId());
		record.setUserId(info.getUserId());
		record.setOrderNo(info.getOrderNo());
		record.setDeleteStatus(DeleteStatus.USEING.shortValue());;
		Orders order = ordersMapper.selectOne(record);
		if(order == null) {
			logger.error("根据orderID，userid查询订单信息为空，userid={},orderid={},orderNo={}",info.getUserId(),info.getOrderId(),info.getOrderNo());
			return Response.error("订单信息不存在，请刷新页面重新操作");
		}
		if(order.getStatus() == Short.valueOf(OrderStatus.REFUNDING_CODE)) {//
			return new Response<>(ResultEnum.SUCCESS,info.getOrderId().toString());
		}
		//退款申请
		Refund refund = new Refund();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		String millTime = sdf.format(new Date());
		String orderNo = "";
		orderNo = millTime+new Random().nextInt(100000);
		refund.setRefundNo(orderNo);
		refund.setOrderId(order.getOrderId());
		refund.setRefundFee(order.getOnlinePayMoney());
		refund.setRefundReason(info.getRefundReason());
		refund.setType(info.getRefundType().shortValue());
		int incount = refundMapper.insert(refund);
		if(incount!=1) {
			logger.error("申请退款异常:新增退款条目不正确",incount);
			throw new RuntimeException("申请退款失败");
		}
		//更新订单信息
		order.setStatus(Short.valueOf(OrderStatus.REFUNDING_CODE));
		int  updatacounr = ordersMapper.updateByPrimaryKeySelective(order);
		if(updatacounr!=1) {
			logger.error("申请退款异常:更新订单状态失败",updatacounr);
			throw new RuntimeException("申请退款失败");
		}
		return new Response<>(ResultEnum.SUCCESS,info.getOrderId().toString());
	}

	@Override
	public Response<String> delOrder(Integer orderId, Integer userId) {
//		Orders record = new Orders();
//		record.setOrderId(orderId);
//		record.setUserId(userId);
//		record.setDeleteStatus(DeleteStatus.USEING.shortValue());;
//		Orders order = ordersMapper.selectOne(record);
		Orders order = ordersMapper.selectByOrderId(orderId,userId);
		if(order == null) {
			logger.error("根据orderID，userid查询订单信息为空，userid={},orderid={}",userId,orderId);
			return Response.error("订单信息不存在，请刷新页面重新操作");
		}
		if(order.getStatus() != Integer.parseInt(OrderStatus.CLOSE_CODE)) {
			logger.error("订单未关闭,删除失败,userId={},orderid={}",userId,orderId);
			return Response.error("订单没有关闭，不能删除!");
		}
		order.setDeleteStatus(DeleteStatus.DELETE.shortValue());
		int upcount = ordersMapper.updateByPrimaryKeySelective(order);
		if(upcount != 1) {
			logger.error("订单删除失败，更新的条目不正确,count={}",upcount);
			throw new RuntimeException("删除订单失败");
		}
		return Response.success("成功");
	}

	@Override
	public LayTableResponse<com.qzdsoft.eshop.vo.orders.OrdersInfo> slectOrdersByQuery(OrdersQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		if(info.getEndTime()!=null && info.getEndTime()!="") {
			info.setEndTime(DateUtil.getSpecifiedDayAfter(info.getEndTime()));
		}
		List<com.qzdsoft.eshop.vo.orders.OrdersInfo> lists = ordersQueryMapper.findByQuery(info);
		return new LayTableResponse<>(lists);
	}

	@Override
	public OrderDetailInfo selectOrderDetailByOrderI(Integer orderId) {
		OrderDetailInfo info = ordersQueryMapper.selectByOrderId(orderId);
		List<OrderGoodsItem> goods = info.getGoods();
		if(!CollectionUtils.isEmpty(goods)) {
			for(OrderGoodsItem g:goods) {
				if(g.getSpecs()!=null && !"".equals(g.getSpecs())) {
					String[] specId = g.getSpecs().split(",");
					StringBuffer sb = new StringBuffer();
					int i = 0;
					for(String s : specId) {
						String name = goodsPropertiesSpecMapper.selectByPrimaryKey(Integer.parseInt(s.split(":")[1])).getName();
						if(i ==0) {
							sb.append(name);
							i++;
						}else {
							sb.append(","+name);
						}
					}
					g.setSpecs(sb.toString());
				}
			}
		}
		return info;
	}

	@Override
	public OrderAddressInfo selectAddressInfo(Integer orderId) {
		return ordersQueryMapper.selectaddByOrid(orderId);
	}

	@Override
	public Response deliveryConfirm(ExpressInfo info) {
		if(info.getOrderId() == null) {
			logger.error("订单id为空");
			return Response.error("订单不存在，发货失败!");
		}
		Orders order = ordersMapper.selectByPrimaryKey(info.getOrderId());
		if(order==null) {
			logger.error("根据订单id查询数据为空，id={}",info.getOrderId());
			return Response.error("订单不存在，发货失败!");
		}
		ExpressCompany company = expressCompanyMapper.selectByPrimaryKey(info.getCompanyId());
		if(company==null) {
			logger.error("根据companyid查询数据为空，id={}",info.getCompanyId());
			return Response.error("物流公司不存在，发货失败!");
		}
		if(info.getExpressId() == null) {//新增
			ExpressInfo ei = new ExpressInfo();
			ei.setOrderId(order.getOrderId());
			ei.setDeliveryTime(new Date());
			ei.setExpressCompanyCode(company.getCode());
			ei.setCompanyId(company.getCompanyId());
			ei.setExpressNo(info.getExpressNo());
			int count = expressInfoMapper.insert(ei);
			if(count!=1) {
				logger.error("插入物流信息失败，更新条目不正确",count);
				throw new RuntimeException("发货失败");
			}
			 order.setStatus(Short.valueOf(OrderStatus.DELIVERED_CODE));
			 int updatacount = ordersMapper.updateByPrimaryKeySelective(order);
			if(updatacount!=1) {
				logger.error("更新订单发货状态失败，更新条目不正确",updatacount);
				throw new RuntimeException("发货失败");
			}
		}else{//修改
			ExpressInfo record = expressInfoMapper.selectByPrimaryKey(info.getExpressId());
			if(record == null) {
				logger.error("根据expressId查询数据为空，expressId={}",info.getExpressId());
				return Response.error("物流信息不存在，修改物流失败!");
			}
			record.setCompanyId(info.getCompanyId());
			record.setExpressNo(info.getExpressNo());
			record.setExpressCompanyCode(company.getCode());
			int upcount = expressInfoMapper.updateByPrimaryKeySelective(record);
			if(upcount!=1) {
				logger.error("更新发货信息失败，更新条目不正确",upcount);
				throw new RuntimeException("修改物流失败");
			}
		}
		return new Response(ResultEnum.SUCCESS);
	}

	@Override
	public OrderExpressInfo selectOrderGoodsbyOrderId(Integer orderId) {
		com.qzdsoft.eshop.vo.express.OrderExpressInfo orderExpress = expressInfoQueryMapper.getOrderExpressInfo(orderId);
		Orders order = ordersMapper.selectByPrimaryKey(orderId);
		List<OrderGoodsItem> goods  = ordersQueryMapper.selectOrderItemsByOrderId(orderId);
		OrderExpressInfo info = new OrderExpressInfo();
		info.setGoods(goods);
		info.setOrderId(order.getOrderId());
		info.setOrderNo(order.getOrderNo());
		if(orderExpress!=null) {
			info.setExpressName(orderExpress.getExpressName());
			info.setExpressNo(orderExpress.getExpressNo());
		}
		info.setRefundTotalPrice(order.getOnlinePayMoney());
		return info;
	}

	@Override
	@Transactional
	public Response<BigDecimal> countCarryPrice(Integer orderId, Integer addressId,Integer userId) {
		UserAddress adrecord = new UserAddress();
		adrecord.setUserId(userId);
		adrecord.setAddressId(addressId);
		UserAddress address = userAddressMapper.selectOne(adrecord);
		if(address == null) {
			logger.error("根据addressId userid查询地址信息为空，userid={},orderid={}",userId,addressId);
			return new Response<>(ResultEnum.ERROR.getCode(),"请添加收货地址",null);
		}
		OrdersDetailInfo info = ordersQueryMapper.findOrderById(userId, orderId);
		if(info==null) {
			logger.error("根据orderid,userid查询订单信息为空,orderid:{},userid:{}",orderId,userId);
			return new Response<>(ResultEnum.ERROR.getCode(),"订单信息不存在，请重新提交",null);
		}
		BigDecimal carryPrice = totalCaryPrice(info.getItems(),address.getCityId());
		Orders order = ordersMapper.selectByPrimaryKey(orderId);
		order.setEndCarrige(carryPrice);
		order.setCarrige(carryPrice);
		
		int upcount = ordersMapper.updateByPrimaryKeySelective(order);
		if(upcount!=1) {
			logger.error("运费计算失败,更新订单运费错误。orderid={}",orderId);
			throw new RuntimeException("运费计算失败,请重新提交订单");
		}
		return new Response<>(ResultEnum.SUCCESS,carryPrice!=null?carryPrice.setScale(2, BigDecimal.ROUND_HALF_UP):BigDecimal.ZERO);
	}

	@Override
	@Transactional
	public Response<String> orderSubmit(OrderSubmitInfo info) {
		Integer payment = null;//支付方式
		BigDecimal userBalance = BigDecimal.ZERO;
		Orders orders = ordersMapper.selectByOrderId(info.getOrderId(),info.getUserId());
		if(orders == null) {
			logger.error("根据orderid,userid查询订单信息为空，orderid={},userid={}",info.getOrderId(),info.getUserId());
			return Response.error("提交失败，订单信息不存在");
		}
		if(info.getIsUseBalance() == IsUseBalance.USEING) {
			User user = userMapper.selectByPrimaryKey(info.getUserId());
			if(user.getBalance().compareTo(BigDecimal.ZERO) == 0) {
				logger.error("余额不足,使用余额支付失败,userid={}",info.getUserId());
				return Response.error("提交失败，您的余额不足");
			}
			userBalance = user.getBalance();
			if(info.getPayType() == Integer.parseInt(PayType.ALIPAY_CODE)) {
				payment = Integer.parseInt(PayType.ALIPAY_BALANCE_CODE);
			}else {
				payment = Integer.parseInt(PayType.WXPAY_BALANCE_CODE);
			}
		}else{
			payment = info.getPayType();
		}
		orders.setPayType(payment.shortValue());
		
		UserAddress addrecord = new UserAddress();
		addrecord.setUserId(info.getUserId());
		addrecord.setAddressId(info.getAddressId());
		List<AddressInfo> address = userAddressQueryMapper.selectaddressById(info.getUserId(),info.getAddressId());
		if(CollectionUtils.isEmpty(address)) {
			logger.error("根据addressId,userid查询用户收货地址为空，addressId={},userid={}",info.getAddressId(),info.getUserId());
			return Response.error("请添加收货地址");
		}
		AddressInfo add = address.get(0);
		StringBuffer ad = new StringBuffer();
		if(!StringUtil.isEmpty(add.getProvinceStr()) ) {
			ad.append(add.getProvinceStr());
		}
		if(!StringUtil.isEmpty(add.getCityStr()) ) {
			ad.append(add.getCityStr());
		}
		if(!StringUtil.isEmpty(add.getDistrictStr()) ) {
			ad.append(add.getDistrictStr());
		}
		add.setAddress(ad.toString()+add.getAddress());
		orders.setAddressId(add.getAddressId());
		orders.setAddressInfo(add.getAddress());
		orders.setTrueName(add.getLinkMan());
		orders.setPhone(add.getMobile());
		orders.setUserRemark(info.getUserRemark());
		orders.setInvoice(info.getIsInvoice().shortValue());
		//计算商品总价
		GoodsOrder goodsOrderRecord = new GoodsOrder();
		goodsOrderRecord.setOrderId(orders.getOrderId());
		List<GoodsOrder> goodsOrder = goodsOrderMapper.select(goodsOrderRecord);
		BigDecimal goodsTotalAmount =  BigDecimal.ZERO;//商品总价
		for(GoodsOrder good:goodsOrder) {
			BigDecimal goodTotal = good.getEndPayMoney();//good.getPrice().multiply(new BigDecimal(good.getNum()));
			goodsTotalAmount = goodsTotalAmount.add(goodTotal);
		}
		logger.debug("goodsTotalAmount={}",goodsTotalAmount);
		//运费
		BigDecimal carryPrice = orders.getEndCarrige()!=null?orders.getEndCarrige():BigDecimal.ZERO;//实收运费
		//总金额
		BigDecimal totalAcount = goodsTotalAmount.add(carryPrice);
		BigDecimal onlinePayAccount = totalAcount;
		logger.debug("onlinePayAccount={}",onlinePayAccount);
		BigDecimal balancePayMoney = BigDecimal.ZERO;
		if(info.getIsUseBalance() == IsUseBalance.USEING){//使用余额
			 onlinePayAccount = totalAcount.subtract(userBalance);
			 balancePayMoney = userBalance;
		}
		orders.setOnlinePayMoney(onlinePayAccount);
		orders.setBalancePayMoney(balancePayMoney);
		orders.setPayPrice(totalAcount);
		int upcount = ordersMapper.updateByPrimaryKeySelective(orders);
		if(upcount != 1) {
			logger.error("订单提交失败,orderId={}",info.getOrderId());
			throw new RuntimeException("提交订单失败");
		}
		return new Response<>(ResultEnum.SUCCESS,orders.getOrderNo());
	}
	
	/**
	 * 计算运费
	 * @param info
	 * @param cityId
	 * @return
	 */
	public BigDecimal totalCaryPrice(List<GoodsOrderInfo> info,Integer cityId){
		//归并重复的商品
		Map<Integer,Integer> goodsmap = new HashMap<Integer,Integer>();
		for(GoodsOrderInfo g:info) {
			if(goodsmap.isEmpty()) {
				goodsmap.put(g.getGoodsId(), g.getNum());
			}else{
				if(goodsmap.containsKey(g.getGoodsId())) {
					Integer num = goodsmap.get(g.getGoodsId());
					num+=g.getNum();
					goodsmap.put(g.getGoodsId(), num);
				}else{
					goodsmap.put(g.getGoodsId(), g.getNum());
				}
			}
		}
		//查询商品的运费模板
		List<TemlateCarryModelInfo> templates = new ArrayList<TemlateCarryModelInfo>();
		Iterator<Integer> goodsIds = goodsmap.keySet().iterator();
		Integer totalGoodsNum = 0; 
		while(goodsIds.hasNext()){  
			 	Integer goodsId=goodsIds.next();
	            Integer num = goodsmap.get(goodsId);
	            TemlateCarryModelInfo template = deliveryTemplateQueryMapper.selectByGoodsId(goodsId);
	            template.setGoodsNum(num);
	            templates.add(template);
	            totalGoodsNum += num;
	     }
		//判断用户是否有所属区域
		CarryWayInfo carryway = cityId!=null?getUserIsRegion(cityId,templates):null;
		BigDecimal totalCarryprice = BigDecimal.ZERO;//总运费
		if(carryway!=null) {//有所属的地区
			totalCarryprice = getSecondAmount(carryway.getTemplateId(),carryway,totalGoodsNum);
		}else{//无所属地区
			//去除重复的template
			Map<Integer,TemlateCarryModelInfo> temMap = new HashMap<Integer,TemlateCarryModelInfo>();
			for(TemlateCarryModelInfo tem:templates) {
				if(temMap.isEmpty()) {
					temMap.put(tem.getTemplateId(), tem);
				}else{
					if(temMap.containsKey(tem.getTemplateId())) {
						TemlateCarryModelInfo isExistTemplate= temMap.get(tem.getTemplateId());
						int num = isExistTemplate.getGoodsNum()+tem.getGoodsNum();
						isExistTemplate.setGoodsNum(num);
						temMap.put(tem.getTemplateId(), isExistTemplate);
					}else{
						temMap.put(tem.getTemplateId(), tem);
					}
				}
			}
			//取各个模板默认的配送方式的 多个配送方式时,取最大值
			Iterator<Integer> itTemplateKeys = temMap.keySet().iterator();
			while(itTemplateKeys.hasNext()) {
				Integer itKey = itTemplateKeys.next();
				TemlateCarryModelInfo temInfo = temMap.get(itKey);
				BigDecimal templateMostCarryPrice = BigDecimal.ZERO;
				List<CarryWayInfo> carryways = temInfo.getCarryWays();
				for(CarryWayInfo carry:carryways) {
					if(carry.getIsDefault() == 1) {
						BigDecimal mostPrice = getSecondAmount(temInfo.getTemplateId(),carry,temInfo.getGoodsNum());
						if(mostPrice.compareTo(templateMostCarryPrice) == 1) {
							templateMostCarryPrice = mostPrice;
						}
					}
				}
				totalCarryprice = totalCarryprice.add(templateMostCarryPrice);
				templateMostCarryPrice = BigDecimal.ZERO;
			}
		}
		return totalCarryprice;
	}
	//各个商品总运费
	public BigDecimal getSecondAmount(Integer temid,CarryWayInfo carryway,Integer goodsNum){
		DeliveryTemplate tem = deliveryTemplateMapper.selectByPrimaryKey(temid);
		BigDecimal totalCarryprice = BigDecimal.ZERO;
		BigDecimal num = new BigDecimal(goodsNum);//商品数量
		BigDecimal secondPrice = new BigDecimal("0");
		if(tem.getFreeDelivery() != 1) {//不包邮
			totalCarryprice = carryway.getFirstAmount();
			switch (tem.getPricingMethod()) {//计价方式
			case 1://按件数
				if(num.compareTo(new BigDecimal(carryway.getFirstPiece())) == 1 ) {
					Double duo = Math.ceil((num.subtract(new BigDecimal(carryway.getFirstPiece())).doubleValue() )/carryway.getSecondPiece());
					secondPrice = carryway.getSecondAmount().multiply(new BigDecimal(duo));
				}
				break;
			case 2://按重量
				if(num.compareTo(carryway.getFirstWeight()) == 1 ) {
					Double duo = Math.ceil((num.subtract(carryway.getFirstWeight()).doubleValue())/carryway.getSecondPiece());
					secondPrice = carryway.getSecondAmount().multiply(new BigDecimal(duo));
				}
				break;
			}
			totalCarryprice = totalCarryprice.add(secondPrice);
		}
		return totalCarryprice;
	}
	/**
	 * 判断用户地址是否有所属配送
	 * @param cityId
	 * @param templates
	 * @return
	 */
	public CarryWayInfo getUserIsRegion(Integer addressId,List<TemlateCarryModelInfo> templates) {
		CarryWayInfo info = null;
		Boolean flag = true;
		for(TemlateCarryModelInfo t:templates) {
			if(flag) {
				for(CarryWayInfo c:t.getCarryWays()) {
					if(c.getRegion().indexOf(addressId.toString())!=-1){  
						info = c;
						flag = false;
						break;
					}
				} 
			}
		}
		return info;
	}
	
	@Override
    public OrderPayInfo selectOrderPayInfo(String orderNo,Integer userId)
    {
        Orders order = ordersMapper.selectByOrderNo(orderNo,userId);
        if(order==null) {
            logger.error("订单号错误，orderNo={}",orderNo);
            throw new RuntimeException("订单号错误，orderNo="+orderNo) ;
        }
        if(order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE) || order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE)) {
        	User user = userMapper.selectByPrimaryKey(order.getUserId());
        	if(user.getBalance().compareTo(order.getBalancePayMoney()) == -1) {
        		logger.error("支付余额不足，userid={},orderid={}",user.getUserId(),order.getOrderId());
        		throw new RuntimeException("支付余额不足，请使用其他支付方式") ;
        	}
        }
        OrderPayInfo payInfo = new OrderPayInfo();
        payInfo.setOrderId(order.getOrderId());
        payInfo.setOrderNo(order.getOrderNo());
        payInfo.setPayMoney(order.getOnlinePayMoney());
        if(order.getPayType() == Integer.parseInt(PayType.WXPAY_CODE) 
        		|| order.getPayType() == Integer.parseInt(PayType.WXPAY_BALANCE_CODE)) {
        	Map<String,Object> params = new HashedMap<>();
        	params.put("orderNo", orderNo);
        	params.put("desc","亿慧云商城购买商品");
        	params.put("totalAmount", order.getOnlinePayMoney());
        	try
        	{
        		params.put("ip",WebToolUtils.getLocalIP());
        	}
        	catch (UnknownHostException | SocketException e)
        	{
        		e.printStackTrace();
        	}
        	Response<Map<Object,Object>> response = weixinPayService.unifiedorder(params);
        	String codeUrl = (String)response.getResult().get("codeUrl");
        	payInfo.setWxPayUrl(codeUrl);
        	payInfo.setPayType(Integer.parseInt(PayType.WXPAY_CODE));
        	logger.debug("获取的微信codeUrl:{}",codeUrl);
        }else{
        	payInfo.setPayType(Integer.parseInt(PayType.ALIPAY_CODE));
        }
        return payInfo;
    }

	@Override
	public Response<String> queryOrderState(String orderNo, Integer userId) {
		Orders order = ordersMapper.selectByOrderNo(orderNo,userId);
		if(order == null) {
			logger.error("根据orderNo,userId查询订单信息为空，orderNo={},userId={}",orderNo,userId);
			return Response.error("订单不存在");
		}
		if(Short.valueOf(OrderStatus.PAID_CODE) == order.getStatus()) {//支付成功
			return new Response<>(ResultEnum.SUCCESS,order.getOrderId().toString());
		}else{
			return Response.error("");
		}
	}
	/**
	 *评价保存 
	 */
	@Override
	public Response<String> commentsave(CommentsEditInfo info, Integer userId) {
		List<CommentsInfo> commentlist = info.getItems();
		List<Comment> addlist = new ArrayList<Comment>();
		List<CommentImg> addImglist = new ArrayList<CommentImg>();
		for(CommentsInfo csi:commentlist) {
			Comment comment = new Comment();
			comment.setSkuId(csi.getSkuId());
			comment.setGoodsId(csi.getGoodsId());
			comment.setContent(csi.getConent());
			comment.setUserId(userId);
			comment.setCtime(new Date());
			addlist.add(comment);
		}
		if(addlist.size()>0) {
			int num = commentMapper.insertList(addlist);
			if(num!=addlist.size()) {
				throw new RuntimeException("保存失败，保存数量错误");
			}
		}
		for(Comment ct:addlist) {
			Comment coet = commentMapper.selectOne(ct);
			for(CommentsInfo csi:commentlist) {
				if(csi.getGoodsId().equals(coet.getGoodsId())&&csi.getSkuId().equals(coet.getSkuId())&&userId.equals(coet.getUserId())) {
					String[] ids = csi.getIds().split(",");
					for(String s : ids) {
						CommentImg commentImg = new CommentImg();
						commentImg.setCommentId(coet.getCommentId());
						commentImg.setAccessoryId(Integer.parseInt(s));
						addImglist.add(commentImg);
					}
				}
			}
		}
		if(addImglist.size()>0) {
			int num = commentImgMapper.insertList(addImglist);
			if(num!=addImglist.size()) {
				throw new RuntimeException("保存失败，保存数量错误");
			}
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

	
	
}
