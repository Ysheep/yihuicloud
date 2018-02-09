package com.qzdsoft.eshop.mapper.goods;

import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.GoodsCommentInfo;

public interface CommentQueryMapper  {
	
	List<GoodsCommentInfo> findGoodComment(Integer goodsId);
}