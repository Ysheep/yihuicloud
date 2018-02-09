package com.qzdsoft.eshop.mapper.shoppingcart;

import java.util.List;

import com.qzdsoft.eshop.domain.goods.ShoppingCart;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartItemInfo;

public interface ShoppingCartQueryMapper extends MyMapper<ShoppingCart> {
	
	List<ShoppingCartItemInfo> findCartInfo(Integer userId);
}