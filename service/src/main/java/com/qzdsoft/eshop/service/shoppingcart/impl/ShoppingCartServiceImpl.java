package com.qzdsoft.eshop.service.shoppingcart.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.goods.ShoppingCart;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesSpecMapper;
import com.qzdsoft.eshop.mapper.shoppingcart.ShoppingCartMapper;
import com.qzdsoft.eshop.mapper.shoppingcart.ShoppingCartQueryMapper;
import com.qzdsoft.eshop.service.shoppingcart.ShoppingCartService;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartDelInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartEditInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartItemInfo;
import com.qzdsoft.vo.DeleteStatus;
import com.qzdsoft.vo.Response;
/**
 * 
 * 购物车
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月30日
 * @see
 * @since 1.0
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	@Autowired
	private ShoppingCartQueryMapper shoppingCartQueryMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private GoodsPropertiesSpecMapper goodsPropertiesSpecMapper;
	
	@Override
	public ShoppingCartInfo findCartInfo(Integer userId) {
		List<ShoppingCartItemInfo> lists = shoppingCartQueryMapper.findCartInfo(userId);
		BigDecimal total = new BigDecimal("0");
		for(ShoppingCartItemInfo g:lists) {
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
			total= total.add(g.getTotalPrice());
		}
		ShoppingCartInfo info = new ShoppingCartInfo();
		info.setTotalNum(lists.size());
		info.setItems(lists);
		info.setTotalPeice(total);
		return info;
	}

	@Override
	@Transactional
	public Response<String> cartAdd(ShoppingCartEditInfo info) {
		
		ShoppingCart record = new ShoppingCart();
		record.setSkuId(info.getSkuId());
		record.setUserId(info.getUserId());
		record.setDeleteStatus(DeleteStatus.USEING.shortValue());
		ShoppingCart result = shoppingCartMapper.selectOne(record);
		if(info.getNum()==null || info.getNum() == 0) {
			info.setNum(1);
		}
		if(result!=null) {
			result.setNum(result.getNum()+info.getNum());
			int upcount= shoppingCartMapper.updateByPrimaryKeySelective(result);
			if(upcount!=1) {
				logger.error("添加购物车失败，更新条目不正确");
				throw new RuntimeException("添加购物车失败");
			}
		}else{
			ShoppingCart cart = new ShoppingCart();
			cart.setDeleteStatus(Short.valueOf("0"));
			cart.setNum(info.getNum());
			cart.setSkuId(info.getSkuId());
			cart.setUserId(info.getUserId());
			cart.setDeleteStatus(DeleteStatus.USEING.shortValue());
			int incount= shoppingCartMapper.insert(cart);
			if(incount!=1) {
				logger.error("添加购物车失败，更新条目不正确");
				throw new RuntimeException("添加购物车失败");
			}
		}
		return Response.success("添加成功");
	}

	@Override
	@Transactional
	public Response<String> del(ShoppingCartDelInfo info) {
		int decount = 0;
		if(info.getIds().size() == 0) {
			logger.error("删除购物城失败，提交id为空");
			return Response.error("删除失败");
		}
		for(Integer id:info.getIds()) {
			ShoppingCart cart = new ShoppingCart();
			cart.setCartId(id);
			cart.setUserId(info.getUserId());
			cart.setDeleteStatus(DeleteStatus.USEING.shortValue());
			ShoppingCart record = shoppingCartMapper.selectOne(cart);
			record.setDeleteStatus(DeleteStatus.DELETE.shortValue());
			decount += shoppingCartMapper.updateByPrimaryKeySelective(record);
		}
		if(decount!=info.getIds().size()) {
			logger.error("删除失败");
			throw new RuntimeException("删除购物车失败");
		}
		return Response.success("删除成功");
	}

	@Override
	@Transactional
	public Response<String> updateCart(Integer cartId, Integer num,Integer userId) {
		ShoppingCart record = new ShoppingCart();
		record.setUserId(userId);
		record.setCartId(cartId);
		record.setDeleteStatus(DeleteStatus.USEING.shortValue());
		ShoppingCart cart = shoppingCartMapper.selectOne(record);
		if(cart==null) {
			logger.error("更新购物车数量失败，cartid={},userId={}",cartId,userId);
			return Response.error("添加购物车失败");
		}
		cart.setNum(num);
		int upcount = shoppingCartMapper.updateByPrimaryKeySelective(cart);
		if(upcount!=1) {
			logger.error("更新购物车数量失败，cartid={},userId={}",cartId,userId);
			throw new RuntimeException("更新购物车数量失败");
		}
		return Response.success("成功");
	}
	
	
}
