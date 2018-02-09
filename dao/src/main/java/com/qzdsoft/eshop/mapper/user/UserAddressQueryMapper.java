package com.qzdsoft.eshop.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.user.UserAddress;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;

public interface UserAddressQueryMapper{
	
	List<AddressInfo> selectaddressById(@Param("userId")Integer userId,@Param("addressId")Integer addressId);

	UserAddress getLatelyAddress(Integer userId);
	
	AddressInfo selectDefaultAddress(@Param("userId")Integer userId);
	
}