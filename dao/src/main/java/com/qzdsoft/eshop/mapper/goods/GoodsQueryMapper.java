/**
 * 
 */
package com.qzdsoft.eshop.mapper.goods;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.goods.GoodsImgInfo;
import com.qzdsoft.eshop.vo.goods.GoodsInfo;
import com.qzdsoft.eshop.vo.goods.GoodsListInfo;
import com.qzdsoft.eshop.vo.goods.GoodsQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchInfo;
import com.qzdsoft.eshop.vo.goods.pc.GoodsSearchQueryInfo;
import com.qzdsoft.eshop.vo.goods.pc.PackageGoodsInfo;
import com.qzdsoft.eshop.vo.goods.pc.StartGoodsInfo;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public interface GoodsQueryMapper
{
    
    List<GoodsListInfo> findByClass(@Param("classid")Integer classid,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("startPage")Integer startPage,@Param("limitCounter") Integer limitCounter);
    /**
     * 查询线下
     * @param classid
     * @param counterNo
     * @param startDate
     * @param endDate
     * @return
     */
    List<GoodsListInfo> selectOffLineByClass(@Param("classid")Integer classid,@Param("counterNo")String counterNo,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("startPage")Integer startPage,@Param("limitCounter") Integer limitCounter);
    /**
     * 根据商品id查询图片列表
     * @param goodsId
     * @return
     */
    List<String> findGoodsImages(Integer goodsId);
    
    List<GoodsInfo> findAllGoodsInfo(GoodsQueryInfo info);
    
    List<GoodsImgInfo> findImg(@Param("goodsId") Integer goodsId);
    /**
     * 获取线上商品
     * @param info
     * @return
     */
    List<TypeInfo> getOnlineGoods(GoodsQueryInfo info);
    
    List<GoodsListInfo> getRecommentGoods(@Param("page")Integer page,@Param("limit")Integer limit);
    
    /**
     * 获取字典中的系统
     * @param info
     * @return
     */
    List<TypeInfo> getAllSystemId();
    /**
     * 商品关键字查询
     * @param keyWord
     * @return
     */
    List<GoodsSearchInfo> goodsSearchByQuery(GoodsSearchQueryInfo info);
    /**
     * 查询明星产品
     * @param currentTime
     * @return
     */
    List<StartGoodsInfo> queryStartGoods(Date currentTime);
    
    List<PackageGoodsInfo> queryPackageGoods(Date currentTime);
    
    List<TypeInfo> findGoodsParams(Integer goodsId);
}
