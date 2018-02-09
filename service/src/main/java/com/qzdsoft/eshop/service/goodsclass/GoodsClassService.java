/**
 * 
 */
package com.qzdsoft.eshop.service.goodsclass;

import java.util.List;

import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesSpecInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassImage;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.pc.ShowIndexClassInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

/**
 * @author pc-20170422
 *
 */
public interface GoodsClassService {
	
	/**
	 * 查商品分类
	 * @return
	 */
	List<GoodsClassInfo> findGoodsClass(GoodsClassQuery info);

	 /**
	 * 查询分类
	 * @param classId
	 * @return
	 */
	GoodsClass findById(Integer classId);

	List<ZtreeNode> findZtree();
	/**
	 * 分类新增
	 * @param info
	 * @return
	 */
	Response<String> saveUpd(GoodsClass info);
	/**
	 * 是否下级
	 * @param pid
	 * @param classId
	 * @return
	 */
	Response<String> isSubordinate(Integer pid,Integer classId);
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	Response<String> del(Integer id);
	
	/**
	 * 获取属性和属性值
	 * @param classId
	 * @return
	 */
	List<GoodsPropertiesSpecInfo> getproperties(Integer classId);
	/**
	 * 首页分类
	 * @return
	 */
	List<GoodsClassImage> queryAllClass();
	/**
	 * 显示在首页的分类
	 * @return
	 */
	List<ShowIndexClassInfo> queryShowIndexClass();
	
	List<TypeInfo> queryGoodsClassForTypeInfo();
}
