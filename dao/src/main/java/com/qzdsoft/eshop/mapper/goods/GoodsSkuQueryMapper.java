/**
 * 
 */
package com.qzdsoft.eshop.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.goods.pc.GoodsSkuDetail;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月27日
 * @see
 * @since 1.0
 */
public interface GoodsSkuQueryMapper {
	
	GoodsSkuDetail findSkuInfo(@Param("goodsId")Integer goodsId,@Param("specIds")String specIds);
}
