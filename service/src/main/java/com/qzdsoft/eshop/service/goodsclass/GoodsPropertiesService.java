/**
 * 
 */
package com.qzdsoft.eshop.service.goodsclass;

import java.util.List;

import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.domain.goodsclass.GoodsPropertiesSpec;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassProInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.GoodsProSpecInfo;
import com.qzdsoft.vo.Response;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月23日
 * @see
 * @since 1.0
 */
public interface GoodsPropertiesService {
	
	/**
	 * 查商品分类属性
	 * @return
	 */
	List<GoodsClassProInfo> findGoodsClassPro(GoodsClassQuery info);

	Response<String> deletPro(Integer id);
	
	/**
	 * 查询属性
	 * @param propertiesId 属性id
	 * @return
	 */
	GoodsProperties findByProId(Integer propertiesId);

	Response<String> saveUpdpro(GoodsProperties info);

	/**
	 * 查商品分类属性值
	 * @return
	 */
	List<GoodsPropertiesSpec> findGoodsProSpec(Integer propertyId);
	/**
	 * 编辑属性值
	 * @param info
	 * @return
	 */
	Response<String> editProSpec(GoodsProSpecInfo info);
	/**
	 * 删除属性值
	 * @param info
	 * @return
	 */
	Response<String> deletSpec(GoodsProSpecInfo info);
}
