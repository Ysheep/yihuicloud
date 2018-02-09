/**
 * 
 */
package com.qzdsoft.eshop.service.address.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.domain.user.UserAddress;
import com.qzdsoft.eshop.mapper.deliverytemplate.AreaMapper;
import com.qzdsoft.eshop.mapper.user.UserAddressMapper;
import com.qzdsoft.eshop.mapper.user.UserAddressQueryMapper;
import com.qzdsoft.eshop.service.address.AddressService;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.AddressDefaultStatus;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月29日
 * @see
 * @since 1.0
 */
@Service
public class AddressServiceImpl implements AddressService {
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddressServiceImpl.class);
	@Autowired
	private UserAddressQueryMapper userAddressQueryMapper;
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public List<AddressInfo> getUserAddress(Integer userId) {
		List<AddressInfo> lists = userAddressQueryMapper.selectaddressById(userId,null);
		if(!CollectionUtils.isEmpty(lists)) {
			for(AddressInfo a:lists) {
				StringBuffer specsIds = new StringBuffer();
				if(!StringUtil.isEmpty(a.getProvinceStr()) ) {
					specsIds.append(a.getProvinceStr());
				}
				if(!StringUtil.isEmpty(a.getCityStr()) ) {
					specsIds.append(a.getCityStr());
				}
				if(!StringUtil.isEmpty(a.getDistrictStr()) ) {
					specsIds.append(a.getDistrictStr());
				}
				a.setAddress(specsIds.toString()+a.getAddress());
			}
		}
		return lists;
	}

	@Override
	@Transactional
	public Response<String> setDefault(Integer userId, Integer addressId) {
		UserAddress record = new UserAddress();
		record.setAddressId(addressId);
		record.setUserId(userId);
		UserAddress address = userAddressMapper.selectOne(record);
		if(address==null) {
			logger.error("根据addressId,userId查询收货地址为空，设置默认地址失败!,addressid={},userid={}",addressId,userId);
			throw new RuntimeException("设置失败，重新设置");
		}
		UserAddress derecord = new UserAddress();
		derecord.setUserId(userId);
		derecord.setIsdefault(Integer.parseInt(AddressDefaultStatus.DEFAULT_CODE));
		UserAddress defaultad = userAddressMapper.selectOne(derecord);
		if(defaultad !=null) {
			defaultad.setIsdefault(Integer.parseInt(AddressDefaultStatus.NOT_DEFAULT_CODE));
			int updefault = userAddressMapper.updateByPrimaryKey(defaultad);
			if(updefault!=1) {
				logger.error("更新默认地址错误，id={}",defaultad.getAddressId());
				throw new RuntimeException("更新默认地址失败,更新条目不正确");
			}
		}
		address.setIsdefault(Integer.parseInt(AddressDefaultStatus.DEFAULT_CODE));
		int updefault = userAddressMapper.updateByPrimaryKey(address);
		if(updefault!=1) {
			logger.error("更新默认地址错误，id={}",address.getAddressId());
			throw new RuntimeException("更新默认地址失败,更新条目不正确");
		}
		return Response.success("成功");
	}

	@Override
	@Transactional
	public Response<String> delAddress(int userId, Integer addressId) {
		if(addressId == null) {
			throw new RuntimeException("删除失败");
		}
		List<AddressInfo> lists = userAddressQueryMapper.selectaddressById(userId,addressId);
		if(CollectionUtils.isEmpty(lists)) {
			logger.error("根据userId,addressId查询收货地址信息为空,userId={},addressId={}",userId,addressId);
			throw new RuntimeException("删除失败");
		}
		int delcount = userAddressMapper.deleteByPrimaryKey(addressId);
		if(delcount != 1) {
			logger.error("删除地址错误，根据id删除地址条目不正确，count={}",delcount);
			throw new RuntimeException("删除失败");
		}
		if(lists.get(0).getIsDefault() == 1) {
			UserAddress latelyAddress = userAddressQueryMapper.getLatelyAddress(userId);
			if(latelyAddress!=null) {
				latelyAddress.setIsdefault(1);
				int updatecount = userAddressMapper.updateByPrimaryKeySelective(latelyAddress);
				if(updatecount != 1) {
					logger.error("删除默认地址，更新一条默认地址错误，根据更新条目不正确，count={}",updatecount);
					throw new RuntimeException("删除失败");
				}
			}
		}
		return new Response<>(ResultEnum.SUCCESS);
	}

	@Override
	@Transactional
	public Response<String> saveOrUpdate(AddressInfo info,Integer userId) {
		if(info.getAddressId()!=null) {//更新
			UserAddress record = new UserAddress();
			record.setAddressId(info.getAddressId());
			record.setUserId(userId);
			UserAddress useraddress = userAddressMapper.selectOne(record);
			if(useraddress == null) {
				logger.error("根据id，userid查询收货地址为空，id={},userId={}",info.getAddressId(),userId);
				return Response.error("修改失败");
			}
			useraddress.setAddressInfo(info.getAddress());
			useraddress.setTrueName(info.getLinkMan());
			useraddress.setPhone(info.getMobile());
			
			useraddress.setProvinceId(info.getProvinceId());
			useraddress.setCityId(info.getCityId());
			useraddress.setDistrictId(info.getDistrictId());
			useraddress.setZip(info.getZip());
			int upcount = userAddressMapper.updateByPrimaryKeySelective(useraddress);
			if(upcount!=1) {
				logger.error("更新收货地址失败，更新条数不正确，upcount={},addressId={}",upcount,useraddress.getAddressId());
				throw new RuntimeException("修改收货地址失败");
			}
		}else{
			UserAddress address = new UserAddress();
			
			address.setUserId(userId);
			address.setAddressInfo(info.getAddress());
			address.setTrueName(info.getLinkMan());
			address.setPhone(info.getMobile());
			address.setAddTime(new Date());
			address.setDeleteStatus(Short.valueOf("0"));
			address.setProvinceId(info.getProvinceId());
			address.setCityId(info.getCityId());
			address.setDistrictId(info.getDistrictId());
			address.setZip(info.getZip());
			AddressInfo degaultAdd = userAddressQueryMapper.selectDefaultAddress(userId);
			if(degaultAdd==null) {
				address.setIsdefault(1);
			}else{
				address.setIsdefault(0);
			}
			int incount = userAddressMapper.insert(address);
			if(incount!=1) {
				logger.error("新增收货地址失败，更新条数不正确，upcount={},userId={}",incount,userId);
				throw new RuntimeException("新增收货地址失败");
			}
		}
		return Response.success("成功");
	}

	@Override
	public List<Area> getAreaById(Integer deepLevel, Integer pid) {
		List<Area> lists = areaMapper.selectById(deepLevel, pid);
		return lists;
	}

	@Override
	public AddressInfo findaddressById(Integer id, Integer userId) {
		List<AddressInfo> list = userAddressQueryMapper.selectaddressById(userId, id);
		AddressInfo info = new AddressInfo();
		if(!CollectionUtils.isEmpty(list)) {
			info = list.get(0);
		}
		return info;
	}

	@Override
	public AddressInfo selectDefault(Integer userId) {
		AddressInfo info = userAddressQueryMapper.selectDefaultAddress(userId);
		if(info!=null) {
			StringBuffer specsIds = new StringBuffer();
			if(!StringUtil.isEmpty(info.getProvinceStr()) ) {
				specsIds.append(info.getProvinceStr());
			}
			if(!StringUtil.isEmpty(info.getCityStr()) ) {
				specsIds.append(info.getCityStr());
			}
			if(!StringUtil.isEmpty(info.getDistrictStr()) ) {
				specsIds.append(info.getDistrictStr());
			}
			info.setAddress(specsIds.toString()+info.getAddress());
		}
		return info;
	}
	
	
}
