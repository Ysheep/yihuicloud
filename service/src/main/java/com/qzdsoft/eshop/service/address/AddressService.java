package com.qzdsoft.eshop.service.address;

import java.util.List;

import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;
import com.qzdsoft.vo.Response;

public interface AddressService {
	
	/**
	 * 查询用户的收货地址
	 * @param wxId
	 * @return
	 */
	List<AddressInfo> getUserAddress(Integer userId);
	/**
	 * 设置默认地址
	 * @param userId
	 * @param addressId
	 * @return
	 */
	Response<String> setDefault(Integer userId,Integer addressId);
	/**
	 * 删除地址
	 * @param i
	 * @param id
	 * @return
	 */
	Response<String> delAddress(int userId, Integer addressId);
	/**
	 * 收货地址新增修改
	 * @param info
	 * @return
	 */
	Response<String> saveOrUpdate(AddressInfo info,Integer userId);
	/**
	 * 获取地区信息
	 * @param deepLevel
	 * @param pid
	 * @return
	 */
	List<Area> getAreaById(Integer deepLevel, Integer pid);
	/**
	 * 查询地址信息
	 * @param id
	 * @param userId
	 * @return
	 */
	AddressInfo findaddressById(Integer id,Integer userId);
	
	/**
	 * 查询默认的地址
	 * @param wxId
	 * @return
	 */
	AddressInfo selectDefault(Integer userId);
	
}
