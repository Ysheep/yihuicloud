package com.qzdsoft.eshop.mapper.goodsclass;

import java.util.List;

import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassProInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;

public interface GoodsPropertiesMapper extends MyMapper<GoodsProperties> {

	List<GoodsClassProInfo> findGoodsClassPro(GoodsClassQuery info);
}