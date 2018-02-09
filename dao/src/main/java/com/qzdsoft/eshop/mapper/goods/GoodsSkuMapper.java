package com.qzdsoft.eshop.mapper.goods;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goods.GoodsSkuInfo;

public interface GoodsSkuMapper extends MyMapper<GoodsSku> {
	
//	List<GoodsOrderInfo> selectBySkuid(@Param("ids")List<Integer> ids);
	
	List<GoodsSkuInfo> selectById(@Param("goodsId")Integer goodsId);
}