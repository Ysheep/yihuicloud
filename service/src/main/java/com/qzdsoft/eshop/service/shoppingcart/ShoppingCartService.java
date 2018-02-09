package com.qzdsoft.eshop.service.shoppingcart;

import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartDelInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartEditInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartInfo;
import com.qzdsoft.vo.Response;

public interface ShoppingCartService {
	/**
	 * 购物车列表
	 * @param userId
	 * @return
	 */
	ShoppingCartInfo findCartInfo(Integer userId);
	/**
	 * 购物车添加
	 * @param info
	 * @return
	 */
	Response<String> cartAdd(ShoppingCartEditInfo info);
	/**
	 * 购物车删除
	 * @param cartIds
	 * @param userId
	 * @return
	 */
	Response<String> del(ShoppingCartDelInfo info);
	/**
	 * 更新购物车的数量
	 * @param cartId
	 * @param num
	 * @return
	 */
	Response<String> updateCart(Integer cartId,Integer num,Integer userId);
}
