package com.qzdsoft.eshop.mapper.goods;

import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.GoodsQuestionInfo;

public interface GoodsQuestionMapper {

	List<GoodsQuestionInfo> findGoodsQuestion(Integer goodsId);
}
