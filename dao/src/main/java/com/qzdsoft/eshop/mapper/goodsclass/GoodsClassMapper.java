package com.qzdsoft.eshop.mapper.goodsclass;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassImage;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.pc.ShowIndexClassInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;

public interface GoodsClassMapper extends MyMapper<GoodsClass> {
	
	List<GoodsClassInfo> findGoodsClass(GoodsClassQuery info);
	
	GoodsClassInfo selectById(@Param("classId")Integer classId);
	
	List<ZtreeNode> findForZtree();

	GoodsClass isSubordinate(@Param("pid")Integer pid,@Param("classId") Integer classId);
	
	List<GoodsClassImage> selectAllClass();
	
	List<ShowIndexClassInfo> selectShowIndexClass();
}