/**
 * 
 */
package com.qzdsoft.eshop.service.goodsclass.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.domain.goodsclass.GoodsPropertiesSpec;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsClassMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsClassQueryMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesSpecMapper;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.vo.goods.GoodsPropertiesSpecInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassImage;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.pc.ShowIndexClassInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.vo.GoodsClassStatus;
import com.qzdsoft.vo.GoodsStatus;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

/**
 * 
 * 商品分类
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月23日
 * @see
 * @since 1.0
 */
@Service
public class GoodsClassServiceImpl implements GoodsClassService {
	
	@Autowired
	private GoodsClassMapper goodsClassMapper;
	@Autowired
	private GoodsPropertiesMapper goodsPropertiesMapper;
	@Autowired
	private GoodsPropertiesSpecMapper goodsPropertiesSpecMapper;
	@Autowired
	private GoodsClassQueryMapper goodsClassQueryMapper;

	@Override
	public List<GoodsClassInfo> findGoodsClass(GoodsClassQuery info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return goodsClassMapper.findGoodsClass(info);
	}

	@Override
	public GoodsClassInfo findById(Integer classId) {
		
		return goodsClassMapper.selectById(classId);
	}
	
	
	
	@Override
	@Transactional
	public Response<String> saveUpd(GoodsClass info) {
			if(info.getClassId() == null) {
				info.setDeleteStatus(Short.valueOf(GoodsClassStatus.USE_CODE));
				goodsClassMapper.insertSelective(info);
			}else {
				goodsClassMapper.updateByPrimaryKeySelective(info);
			}
			return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public List<ZtreeNode> findZtree() {
		List<ZtreeNode> lists = goodsClassMapper.findForZtree();
		return lists;
	}

	@Override
	public Response<String> isSubordinate(Integer pid, Integer classId) {
		// TODO Auto-generated method stub
		GoodsClass gc = goodsClassMapper.isSubordinate(pid,classId);
		if(gc == null) {
			return Response.success("");
		}else{
			return Response.error("该分类是其子分类");
		}
	}

	@Override
	@Transactional
	public Response<String> del(Integer id) {
		GoodsClass record = goodsClassMapper.selectByPrimaryKey(id);
		record.setDeleteStatus(Short.valueOf(GoodsClassStatus.DELET_CODE));
		int delcount = goodsClassMapper.updateByPrimaryKeySelective(record);
		if(delcount!=1) {
			throw new RuntimeException("删除失败");
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}

	@Override
	public List<GoodsPropertiesSpecInfo> getproperties(Integer classId) {
		//根据类获取属性
		GoodsProperties record = new GoodsProperties();
		record.setClassId(classId);
		record.setDeleteStatus(Integer.parseInt(GoodsStatus.USE_CODE));
		List<GoodsProperties> list = goodsPropertiesMapper.select(record);
		List<GoodsPropertiesSpecInfo> listInfo = new ArrayList<GoodsPropertiesSpecInfo>();
		for(GoodsProperties lst: list) {
			GoodsPropertiesSpecInfo gpsi = new GoodsPropertiesSpecInfo();
			gpsi.setPropertyId(lst.getPropertyId());
			gpsi.setPropertyName(lst.getName());
			
			GoodsPropertiesSpec gps = new GoodsPropertiesSpec();
			gps.setDeleteStatus(Integer.parseInt(GoodsStatus.USE_CODE));
			gps.setPropertyId(lst.getPropertyId());
			List<GoodsPropertiesSpec> gpslist = goodsPropertiesSpecMapper.select(gps);
			if(gpslist.size()>0) {
				List<TypeInfo> specname = new ArrayList<TypeInfo>();
				for(GoodsPropertiesSpec e : gpslist) {
					specname.add(new TypeInfo(e.getSpecId().toString(),e.getName()));
				}
				gpsi.setSpec(specname);
				listInfo.add(gpsi);
			}
		}
		return listInfo;
	}

	@Override
	public List<GoodsClassImage> queryAllClass() {
		List<GoodsClassImage> lists = goodsClassMapper.selectAllClass();
		return lists;
	}

	@Override
	public List<ShowIndexClassInfo> queryShowIndexClass() {
		List<ShowIndexClassInfo> lists = goodsClassMapper.selectShowIndexClass();
		return lists;
	}

	@Override
	public List<TypeInfo> queryGoodsClassForTypeInfo() {
		
		return goodsClassQueryMapper.queryGoodsClassForTypeInfo();
	}
	
	
}
