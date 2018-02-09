/**
 * 
 */
package com.qzdsoft.eshop.service.advertisement.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.qzdsoft.eshop.domain.advertisement.Advertisement;
import com.qzdsoft.eshop.mapper.advertisement.AdvertisementMapper;
import com.qzdsoft.eshop.service.advertisement.AdvertisementService;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementInfo;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementQueryInfo;
import com.qzdsoft.utils.DateUtil;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月15日
 * @see
 * @since 1.0
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	private static final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);
	@Autowired
	private AdvertisementMapper advertisementMapper;

	@Override
	public LayTableResponse<AdvertisementInfo> getAllAdvertisements(AdvertisementQueryInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		if(info.getEtime()!=null && info.getEtime()!="") {
			info.setEtime(DateUtil.getSpecifiedDayAfter(info.getEtime()));
		}
		List<AdvertisementInfo> list = advertisementMapper.getAllAdvertisements(info);
		return  new LayTableResponse<AdvertisementInfo>(list);
	}

	@Override
	public AdvertisementInfo selctById(Integer advertisementId) {
		AdvertisementInfo info = advertisementMapper.selectById(advertisementId);
		return info;
	}

	@Override
	@Transactional
	public Response<String> createOrUpdate(Advertisement info) {
		Advertisement record = null;
		if(info.getAdvertId()!=null) {
			record = advertisementMapper.selectByPrimaryKey(info.getAdvertId());
			if(record==null) {//
				logger.error("修改广告信息，根据id查询广告信息为空，id={}",info.getAdvertId());
				return Response.error("修改失败");
			}
			record.setAccessoryId(info.getAccessoryId());
			record.setGoodsId(info.getGoodsId());
			record.setCtime(new Date());
			int upcount = advertisementMapper.updateByPrimaryKey(record);
			if(upcount!=1) {
				logger.error("更新广告信息异常，更新条目不正确，upcount={}",upcount);
				throw new RuntimeException("更新广告信息错误");
			}
			
		}else{
			record = new Advertisement();
			record.setAccessoryId(info.getAccessoryId());
			record.setGoodsId(info.getGoodsId());
			record.setCtime(new Date());
			int incount = advertisementMapper.insert(record);
			if(incount!=1) {
				logger.error("更新广告信息异常，更新条目不正确，upcount={}",incount);
				throw new RuntimeException("插入广告信息错误");
			}
		}
		return Response.success("成功");
	}

	@Override
	@Transactional
	public Response<String> del(Integer adverId) {
		Advertisement record = advertisementMapper.selectByPrimaryKey(adverId);
		if(record == null) {
			logger.error("删除广告信息，根据id查询广告信息为空，id={}",adverId);
			return Response.error("删除失败");
		}
		int delcount = advertisementMapper.delete(record);
		if(delcount!=1) {
			logger.error("删除广告信息异常，删除条目不正确，upcount={}",delcount);
			throw new RuntimeException("删除广告信息错误");
		}
		return Response.success("成功");
	}
	/**
	 * 首页广告
	 */
	@Override
	public List<AdvertisementInfo> list() {
		
		return advertisementMapper.list();
	}
	
	
	
}
