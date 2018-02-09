package com.qzdsoft.eshop.service.goodsclass.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.goodsclass.GoodsProperties;
import com.qzdsoft.eshop.domain.goodsclass.GoodsPropertiesSpec;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesMapper;
import com.qzdsoft.eshop.mapper.goodsclass.GoodsPropertiesSpecMapper;
import com.qzdsoft.eshop.service.goodsclass.GoodsPropertiesService;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassProInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.goodsclass.GoodsProSpecInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsSpecEditInfo;
import com.qzdsoft.vo.GoodsClassStatus;
import com.qzdsoft.vo.GoodsStatus;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月23日
 * @see
 * @since 1.0
 */
@Service
public class GoodsPropertiesServiceImpl implements GoodsPropertiesService {
	private static Logger logger = LoggerFactory.getLogger(GoodsPropertiesServiceImpl.class);
	
	@Autowired
	private GoodsPropertiesMapper goodsPropertiesMapper;
	@Autowired
	private GoodsPropertiesSpecMapper goodsPropertiesSpecMapper;
	
	
	@Override
	public List<GoodsClassProInfo> findGoodsClassPro(GoodsClassQuery info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return goodsPropertiesMapper.findGoodsClassPro(info);
	}


	@Override
	public Response<String> deletPro(Integer id) {
		GoodsProperties record = goodsPropertiesMapper.selectByPrimaryKey(id);
		record.setDeleteStatus(Integer.parseInt(GoodsClassStatus.DELET_CODE));
		goodsPropertiesMapper.updateByPrimaryKeySelective(record);
		return new Response<String>(ResultEnum.SUCCESS);
	}


	@Override
	public GoodsProperties findByProId(Integer propertiesId) {
		return goodsPropertiesMapper.selectByPrimaryKey(propertiesId);
	}


	@Override
	public Response<String> saveUpdpro(GoodsProperties info) {
		if(info.getPropertyId() == null) {
			goodsPropertiesMapper.insertSelective(info);
		}else {
			goodsPropertiesMapper.updateByPrimaryKeySelective(info);
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}


	@Override
	public List<GoodsPropertiesSpec> findGoodsProSpec(Integer propertyId) {
		GoodsPropertiesSpec record = new GoodsPropertiesSpec();
		record.setPropertyId(propertyId);
		record.setDeleteStatus(Integer.parseInt(GoodsClassStatus.USE_CODE));
		return goodsPropertiesSpecMapper.select(record);
	}


	@Override
	@Transactional
	public Response<String> editProSpec(GoodsProSpecInfo info) {
		List<GoodsPropertiesSpec> addlist = new ArrayList<GoodsPropertiesSpec>();
		List<GoodsPropertiesSpec> editlist = new ArrayList<GoodsPropertiesSpec>();
		Integer propertyId = info.getPropertyId();
		
		for(GoodsSpecEditInfo gedit: info.getItems()) {
			if(gedit.getName()!=null && !"".equals(gedit.getName())) {
				GoodsPropertiesSpec gps = new GoodsPropertiesSpec();
				gps.setPropertyId(propertyId);
				gps.setName(gedit.getName());
				
				if(gedit.getSpecId()==null) {
					gps.setDeleteStatus(0);
					addlist.add(gps);
				}else {
					gps.setSpecId(gedit.getSpecId());
					editlist.add(gps);
				}
			}
		}
		int num=0;
		if(addlist.size()!=0) {
			num = goodsPropertiesSpecMapper.insertList(addlist);
		}
		if(num != addlist.size()) {
			throw new RuntimeException("保存失败");
		}
		num=0;
		if(editlist.size()!=0) {
			for(GoodsPropertiesSpec record : editlist) {
				num += goodsPropertiesSpecMapper.updateByPrimaryKeySelective(record);
			}
			if(num != editlist.size()) {
				throw new RuntimeException("保存失败");
			}
		}
		
		return new Response<String>(ResultEnum.SUCCESS);
	}


	@Override
	@Transactional
	public Response<String> deletSpec(GoodsProSpecInfo info) {
		int num=0;
		for(Integer specId: info.getIds()) {
			GoodsPropertiesSpec spec = goodsPropertiesSpecMapper.selectByPrimaryKey(specId);
//			num += goodsPropertiesSpecMapper.deleteByPrimaryKey(specId);
			if(spec == null) {
				logger.error("删除属性值失败，根据属性值id查询数据为空，id={}",specId);
				throw new RuntimeException("删除失败");
			}
			spec.setDeleteStatus(Integer.parseInt(GoodsStatus.DELET_CODE));
			num += goodsPropertiesSpecMapper.updateByPrimaryKeySelective(spec);
		}
		if(num != info.getIds().size()) {
			throw new RuntimeException("删除失败");
		}
		return new Response<String>(ResultEnum.SUCCESS);
	}
	
	
}
