package com.qzdsoft.eshop.service.goods;

import java.util.List;

import com.qzdsoft.eshop.domain.goods.Goods;
import com.qzdsoft.eshop.domain.goods.GoodsParams;
import com.qzdsoft.eshop.domain.goods.GoodsSku;
import com.qzdsoft.eshop.vo.goods.GoodsAddInfo;
import com.qzdsoft.eshop.vo.goods.GoodsDetailInfo;
import com.qzdsoft.eshop.vo.goods.GoodsImgInfo;
import com.qzdsoft.eshop.vo.goods.GoodsInfo;
import com.qzdsoft.eshop.vo.goods.GoodsListInfo;
import com.qzdsoft.eshop.vo.goods.GoodsParamInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesInfo;
import com.qzdsoft.eshop.vo.goods.GoodsPropertyConfigEditInfo;
import com.qzdsoft.eshop.vo.goods.GoodsQueryInfo;
import com.qzdsoft.eshop.vo.goods.GoodsSkuInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsCommentInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsQuestionInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSkuDetail;
import com.qzdsoft.eshop.vo.goods.pc.PackageGoodsInfo;
import com.qzdsoft.eshop.vo.goods.pc.StartGoodsInfo;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;

/**
 * 商品service
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public interface GoodsService {


	/**
	 * 根据查询商品
	 * @return
	 */
	List<GoodsInfo> findAllGoods(GoodsQueryInfo info);
	/**
	 * 根据id查商品
	 * @param goodsId
	 * @return
	 */
	Goods findByGoodsId(Integer goodsId);
	/**
	 * 新增商品
	 * @param goods
	 * @return
	 */
	Response<String> saveadd(GoodsAddInfo info);
	/**
	 * 保存和修改商品
	 * @param goods
	 * @return
	 */
	Response<String> saveUpd(GoodsAddInfo info);
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	Response<String> delet(Integer id);
	

    
    /**
     * 根据id查询商品详情
     * @param goodsid
     * @return
     */
   GoodsDetailInfo findById(Integer goodsid);
    
    /**
     * 根据skuId查询商品详情
     * @param goodsid
     * @return
     */
    GoodsDetailInfo findBySkuid(Integer skuId,String counterNo);
    
    /**
     * 根据商品id查商品图信息
     * @param goodsId
     * @return
     */
    List<GoodsImgInfo> findImgById(Integer goodsId);
    /**
     * 根据商品id查产品信息
     * @param goodsId
     * @return
     */
    List<GoodsSkuInfo> findGoodsSkuById(Integer goodsId);
    /**
     * @param specsIds
     * @return
     */
    GoodsSkuDetail findSkuInfo(Integer goodsId,String specsIds);
    /**
     * 上下架商品
     * @return
     */
	Response<String> undercart(Integer goodsId,Integer type,String start,String end);
	/**
     * 上下架商品
     * @return
     */
	Response<String> isPackage(Integer goodsId,Integer type);
	

    /**
     * 是否推荐
     * @param goodsId
     * @param isRecommend
     * @return
     */
    Response<String> setRecommend(Integer goodsId,Integer isRecommend);
    
    List<TypeInfo> getGoods(GoodsQueryInfo info);
    
    List<GoodsListInfo> getRecommentGoods(Integer page,Integer limit);
    /**
     * 获取所有系统
     * @return
     */
    List<TypeInfo> getAllSystem();
    /**
     * 首页商品查询
     * @param info
     * @return
     */
    Page<GoodsSearchInfo> goodsSearchByQuery(GoodsSearchQueryInfo info);
    
    /**
	 * 查商品参数值
	 * @return
	 */
	List<GoodsParams> findParamsById(Integer goodsId);
	
	Response<String> editParams(GoodsParamInfo info); 
	
	Response<String> deletParam(GoodsParamInfo info); 
	/**
	 * 查询明星产品
	 * @return
	 */
	List<StartGoodsInfo> queryStartGoods();
	/**
	 * 商品列表
	 * @param info
	 * @return
	 */
	 Page<GoodsSearchInfo> goodsList(GoodsSearchQueryInfo info);
	 /**
	  * 查询首页显示的套餐产品
	  * @return
	  */
	 List<PackageGoodsInfo> queryShowIndexPackageGoods();
	 /**
	  * 商品参数
	  * @param goodsId
	  * @return
	  */
	 List<TypeInfo> findGoodsParams(Integer goodsId);
	 /**
	  * 商品评论
	  * @param goodsId
	  * @return
	  */
	 List<GoodsCommentInfo> findGoodsCommentInfo(Integer goodsId);
	 /**
	  * 商品提问及答案
	  * @param goodsId
	  * @return
	  */
	 List<GoodsQuestionInfo> findGoodsQuestionInfo(Integer goodsId);
	 /**
	  * 商品提问
	  * @param goodsId
	  * @return
	  */
	 Response<String> addGoodsQuestion(Integer goodsId,String content,Integer userId);
	 /**
	  * 商品提问回答
	  * @param id 问题id
	  * @return
	  */
	 Response<String> saveanswer(Integer id,String content,Integer userId);
	 /**
	  * 根据商品id查询商品属性
	  * @param goodsId
	  * @return
	  */
	 List<GoodsPropertiesInfo> findGoodsProById(Integer goodsId);
	 /**
	  * 属性设置保存
	  * @param info
	  * @return
	  */
	 Response<String> saveGoodsPros(GoodsPropertyConfigEditInfo info);
}
