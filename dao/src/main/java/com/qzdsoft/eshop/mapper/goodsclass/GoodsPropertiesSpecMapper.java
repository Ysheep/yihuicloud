package com.qzdsoft.eshop.mapper.goodsclass;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.goodsclass.GoodsPropertiesSpec;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSpecInfo;

public interface GoodsPropertiesSpecMapper extends MyMapper<GoodsPropertiesSpec> {
	
	GoodsSpecInfo selectBy(@Param("goodsId")Integer goodsId,@Param("specId")Integer specId);
}