package com.qzdsoft.eshop.mapper.goods;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.goods.Goods;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goods.GoodsDetailInfo;

public interface GoodsMapper extends MyMapper<Goods> {
	
	GoodsDetailInfo selectBySkuid(@Param("skuId") Integer skuId,@Param("counterNo")String counterNo);
}